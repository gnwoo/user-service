package com.gnwoo.userservice;

import com.gnwoo.userservice.data.dto.UserDTO;
import com.gnwoo.userservice.data.repo.PasscodeRepo;
import com.gnwoo.userservice.data.repo.UserRepo;
import com.gnwoo.userservice.data.table.User;
import com.gnwoo.userservice.requestTemplate.ChangePasswordRequest;
import com.gnwoo.userservice.requestTemplate.LoginRequest;
import com.gnwoo.userservice.requestTemplate.SignUpRequest;
import com.gnwoo.userservice.requestTemplate.VerifyByEmailAddressRequest;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.HashMap;
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
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();


    @PostMapping(path="/sign-up")
    public ResponseEntity<HashMap<String, String>> signUp (@RequestBody SignUpRequest req) {
        HashMap<String, String> res = new HashMap<>();

        // check duplicates
        if(!userRepo.findByUsername(req.getUsername()).isEmpty())
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);

        // otherwise, new user
        // hash the password
        String hashed_password = passwordEncoder.encode(req.getPassword());

        User user = new User(req.getUsername(), req.getDisplayName(), req.getEmail(), hashed_password);
        // if user enables 2FA, generate a 2FA secret key
        if(req.getIs2FA())
        {
            final GoogleAuthenticatorKey key = gAuth.createCredentials();
            user.setSecretKey2FA(key.getKey());
            user.setIs2FA(true);
            res.put("secretKey2FA", key.getKey());
        }

        // save the user to the db
        userRepo.save(user);

        // response true to user service
        return new ResponseEntity<>(res, HttpStatus.OK);
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
        if(passwordEncoder.matches(req.getPassword(), user.getHashedPassword())) {
            // if user enables 2FA
            if(user.getIs2FA())
            {
                String passcode_2FA = req.getPasscode2FA();
                // tell frontend to let user input 2FA passcode
                if(passcode_2FA == null)
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                else if(gAuth.authorize(user.getSecretKey2FA(), Integer.parseInt(passcode_2FA)))
                {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("uuid", user.getUuid().toString());
                    return new ResponseEntity<>(new UserDTO(user), headers, HttpStatus.OK);
                }
                // invalid 2FA passcode
                else
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            // otherwise, user does not enable 2FA, return OK here
            HttpHeaders headers = new HttpHeaders();
            headers.add("uuid", user.getUuid().toString());
            return new ResponseEntity<>(new UserDTO(user), headers, HttpStatus.OK);
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

    @PostMapping(path="change-2FA-status")
    public ResponseEntity<HashMap<String, String>> change2FAStatus (@RequestHeader("uuid") String uuid) {
        // get uuid from session
        List<User> relations = userRepo.findByUuid(Long.parseLong(uuid));

        User user = relations.get(0);
        HashMap<String, String> res = new HashMap<>();
        // if user disables 2FA, enable it
        if(!user.getIs2FA())
        {
            user.setIs2FA(true);
            // generate 2FA secret key
            final GoogleAuthenticatorKey key = gAuth.createCredentials();
            user.setSecretKey2FA(key.getKey());
            res.put("new2FAStatus", "true");
            res.put("secretKey2FA", key.getKey());
        }
        // otherwise, the user enables 2FA, disable it
        else
        {
            user.setIs2FA(false);
            res.put("new2FAStatus", "false");
        }

        // save user's new 2FA status to db
        userRepo.save(user);

        // return
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // dump auth-status
    @GetMapping(path="/authentication-status")
    public ResponseEntity<String> authStatus () { return new ResponseEntity<>("authentication ok", HttpStatus.OK); }

    // dump logout
    @PostMapping(path="logout")
    public ResponseEntity<String> logout () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout-everywhere
    @PostMapping(path="logout-everywhere")
    public ResponseEntity<String> logoutEverywhere () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump session-info
    @GetMapping(path="session-info")
    public ResponseEntity<String> sessionInfo () { return new ResponseEntity<>(HttpStatus.OK); }

}