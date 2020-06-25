package com.gnwoo.authservice;

import com.gnwoo.authservice.data.dto.AuthDTO;
import com.gnwoo.authservice.data.repo.AuthRepo;
import com.gnwoo.authservice.data.repo.PasscodeDAO;
import com.gnwoo.authservice.data.table.Auth;
import com.gnwoo.authservice.handlers.JWTHandler;
import com.gnwoo.authservice.requestTemplate.ChangePasswordRequest;
import com.gnwoo.authservice.requestTemplate.LoginRequest;
import com.gnwoo.authservice.requestTemplate.SignUpRequest;
import com.gnwoo.authservice.requestTemplate.VerifyByEmailAddressRequest;
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
public class AuthController {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasscodeDAO passcodeDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTHandler jwtHandler;

    @GetMapping(path="/health")
    public ResponseEntity<String> test () {
        return ResponseEntity.ok().body("health ok");
    }

    @PostMapping(path="/sign-up")
    public ResponseEntity<String> signUp (@RequestBody SignUpRequest req) {
        // check duplicates
        if(!authRepo.findByUsername(req.getUsername()).isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        // otherwise, new user
        // hash the password
        String hashed_password = passwordEncoder.encode(req.getPassword());

        // save the user to the db
        Auth auth = new Auth(req.getUsername(), req.getDisplayName(), req.getEmail(), hashed_password);
        authRepo.save(auth);

        // response true to user service
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="/login")
    public ResponseEntity<AuthDTO> login (@RequestBody LoginRequest req) {
        // get user from db
        String username = req.getUsername();
        List<Auth> relations = authRepo.findByUsername(username);

        // invalid login: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // otherwise, user found
        Auth auth = relations.get(0);

        // valid login: username and password combination matched
        if(passwordEncoder.matches(req.getPassword(), auth.getHashedPassword()))
        {
            // construct a JWT token
            String JWT_token = jwtHandler.consturctJWT(auth.getUuid());

            // clean the hashed password
            auth.setHashedPassword("the password has been hashed and salted");

            // response OK with JWT
            HttpHeaders headers = new HttpHeaders();
            headers.add("Set-Cookie", "JWT=" + JWT_token);
            headers.add("Set-Cookie", "uuid=" + auth.getUuid());
//            headers.add("Set-Cookie", "HttpOnly");
            return new ResponseEntity<>(new AuthDTO(auth), headers, HttpStatus.OK);
//            return ResponseEntity.ok().headers(headers).body(auth);
        }
        // otherwise, invalid login
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path="/verify-by-email-address")
    public ResponseEntity<String> verifyByEmailAddress (@RequestBody VerifyByEmailAddressRequest req) {
        // get user from db
        String username = req.getUsername();
        List<Auth> relations = authRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // otherwise, user found
        Auth auth = relations.get(0);

        // generate a 4-digit passcode
        SecureRandom secureRandom = new SecureRandom();
        String passcode = String.format("%06d", secureRandom.nextInt(1000000));
        System.out.println("passcode: " + passcode);

        // save (uuid, passcode) to Redis
        passcodeDAO.savePasscode(auth.getUuid(), passcode);

        // TODO: rpc call to email service
        String email = auth.getEmail();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path="/change-password")
    public ResponseEntity<String> changePassword (@RequestBody ChangePasswordRequest req) {
        // get user from db
        String username = req.getUsername();
        List<Auth> relations = authRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // otherwise, user found
        Auth auth = relations.get(0);
        Long uuid = auth.getUuid();
        String passcode = passcodeDAO.findByUuid(uuid);

        // valid passcode
        if(passcode != null && passcode.equals(req.getPasscode()))
        {
            // update hashed password in db
            String new_hashed_password = passwordEncoder.encode(req.getNewPassword());
            auth.setHashedPassword(new_hashed_password);
            authRepo.save(auth);

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
    public ResponseEntity<String> authStatus () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout
    @GetMapping(path="logout")
    public ResponseEntity<String> logout () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout-everywhere
    @GetMapping(path="logout-everywhere")
    public ResponseEntity<String> logoutEverywhere () { return new ResponseEntity<>(HttpStatus.OK); }
}