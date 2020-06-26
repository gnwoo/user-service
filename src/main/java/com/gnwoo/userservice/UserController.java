package com.gnwoo.userservice;

import com.gnwoo.userservice.data.dto.UserDTO;
import com.gnwoo.userservice.data.repo.PasscodeRepo;
import com.gnwoo.userservice.data.repo.UserRepo;
import com.gnwoo.userservice.data.table.User;
import com.gnwoo.userservice.util.JWTUtil;
import com.gnwoo.userservice.requestTemplate.ChangePasswordRequest;
import com.gnwoo.userservice.requestTemplate.LoginRequest;
import com.gnwoo.userservice.requestTemplate.SignUpRequest;
import com.gnwoo.userservice.requestTemplate.VerifyByEmailAddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasscodeRepo passcodeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(path="/health")
    public ResponseEntity<String> test () {
        return ResponseEntity.ok().body("health ok");
    }

    @PostMapping(path="/sign-up")
    public ResponseEntity<String> signUp (@RequestBody SignUpRequest req) {
        // check duplicates
        if(!userRepo.findByUsername(req.getUsername()).isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        // otherwise, new user
        // hash the password
        String hashed_password = passwordEncoder.encode(req.getPassword());

        // save the user to the db
        User user = new User(req.getUsername(), req.getDisplayName(), req.getEmail(), hashed_password);
        userRepo.save(user);

        // response true to user service
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="/login")
    public ResponseEntity<UserDTO> login (@RequestBody LoginRequest req) {
        // get user from db
        String username = req.getUsername();
        List<User> relations = userRepo.findByUsername(username);

        // invalid login: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // otherwise, user found
        User user = relations.get(0);

        // valid login: username and password combination matched
        if(passwordEncoder.matches(req.getPassword(), user.getHashedPassword()))
        {
            // construct a JWT token
            String JWT_token = jwtUtil.consturctJWT(user.getUuid());

            // clean the hashed password
            user.setHashedPassword("the password has been hashed and salted");

            // response OK with JWT
            HttpHeaders headers = new HttpHeaders();
            headers.add("Set-Cookie", "JWT=" + JWT_token);
            headers.add("Set-Cookie", "uuid=" + user.getUuid());
//            headers.add("Set-Cookie", "HttpOnly");
            return new ResponseEntity<>(new UserDTO(user), headers, HttpStatus.OK);
//            return ResponseEntity.ok().headers(headers).body(auth);
        }
        // otherwise, invalid login
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path="/verify-by-email-address")
    public ResponseEntity<String> verifyByEmailAddress (@RequestBody VerifyByEmailAddressRequest req) {
        // get user from db
        String username = req.getUsername();
        List<User> relations = userRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // otherwise, user found
        User user = relations.get(0);

        // generate a 4-digit passcode
        SecureRandom secureRandom = new SecureRandom();
        String passcode = String.format("%06d", secureRandom.nextInt(1000000));
        System.out.println("passcode: " + passcode);

        // save (uuid, passcode) to Redis
        passcodeRepo.savePasscode(user.getUuid(), passcode);

        // TODO: rpc call to email service
        String email = user.getEmail();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path="/change-password")
    public ResponseEntity<String> changePassword (@RequestBody ChangePasswordRequest req) {
        // get user from db
        String username = req.getUsername();
        List<User> relations = userRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // otherwise, user found
        User user = relations.get(0);
        Long uuid = user.getUuid();
        String passcode = passcodeRepo.findByUuid(uuid);

        // valid passcode
        if(passcode != null && passcode.equals(req.getPasscode()))
        {
            // update hashed password in db
            String new_hashed_password = passwordEncoder.encode(req.getNewPassword());
            user.setHashedPassword(new_hashed_password);
            userRepo.save(user);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Set-Cookie", "uuid=" + uuid);
            return new ResponseEntity<>("change password ok", headers, HttpStatus.OK);
        }
        // invalid passcode
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // dump auth-status
    @GetMapping(path="/authentication-status")
    public ResponseEntity<String> authStatus () { return new ResponseEntity<>("authentication ok", HttpStatus.OK); }

    // dump logout
    @GetMapping(path="logout")
    public ResponseEntity<String> logout () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout-everywhere
    @GetMapping(path="logout-everywhere")
    public ResponseEntity<String> logoutEverywhere () { return new ResponseEntity<>(HttpStatus.OK); }
}