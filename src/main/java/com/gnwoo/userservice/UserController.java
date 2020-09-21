package com.gnwoo.userservice;

import com.gnwoo.userservice.data.dto.SecretKey2FADTO;
import com.gnwoo.userservice.data.dto.UserInfoDTO;
import com.gnwoo.userservice.exception.DuplicateUsernameException;
import com.gnwoo.userservice.exception.NotFoundException;
import com.gnwoo.userservice.exception.Require2FAException;
import com.gnwoo.userservice.exception.UnauthorizedException;
import com.gnwoo.userservice.requestTemplate.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/sign-up")
    public ResponseEntity<String> signUp (@RequestBody SignUpRequest req) {
        try {
            userService.handleSignUp(req.getUsername(), req.getPassword(), req.getDisplayName(), req.getEmail(),
                    req.getIs2FA());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException | IllegalArgumentException nullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DuplicateUsernameException duplicateUsernameException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path="/sign-up-email-verification")
    public ResponseEntity<SecretKey2FADTO> signUpEmailVerification (@RequestBody SignUpEmailVerificationRequest req) {
        try {
            String secretKey2FA = userService.handleSignUpEmailVerification(req.getEmail(), req.getPasscode());
            SecretKey2FADTO secretKey2FADTO = new SecretKey2FADTO(secretKey2FA);
            return new ResponseEntity<>(secretKey2FADTO, HttpStatus.OK);
        } catch (NullPointerException | IllegalArgumentException nullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException unauthorizedException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="/login")
    public ResponseEntity<UserInfoDTO> login (@RequestBody LoginRequest req) {
        try {
            UserInfoDTO userInfoDTO = userService.handleLogin(req.getUsername(), req.getPassword(),
                    req.getPasscode2FA());
            HttpHeaders headers = new HttpHeaders();
            headers.add("uuid", userInfoDTO.getUuid().toString());
            return new ResponseEntity<>(userInfoDTO, headers, HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException unauthorizedException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Require2FAException require2FAException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path="/verify-by-email-address")
    public ResponseEntity<String> verifyByEmailAddress (@RequestBody VerifyByEmailAddressRequest req) {
        try {
            userService.verifyByEmailAddress(req.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="/change-password")
    public ResponseEntity<String> changePassword (@RequestBody ChangePasswordRequest req) {
        try {
            Long uuid = userService.changePassword(req.getUsername(), req.getPasscode(), req.getNewPassword());
            HttpHeaders headers = new HttpHeaders();
            headers.add("uuid", uuid.toString());
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (NullPointerException | IllegalArgumentException nullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException unauthorizedException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="/change-2FA-status")
    public ResponseEntity<SecretKey2FADTO> change2FAStatus (@RequestHeader("uuid") String uuid) {
        String secretKey2FA = userService.change2FAStatus(Long.parseLong(uuid));
        SecretKey2FADTO secretKey2FADTO = new SecretKey2FADTO(secretKey2FA);
        return new ResponseEntity<>(secretKey2FADTO, HttpStatus.OK);
    }

    @GetMapping(path="/user-info")
    public ResponseEntity<UserInfoDTO> userInfo (@RequestHeader("uuid") String uuid) {
        UserInfoDTO userInfoDTO = userService.getUserInfo(Long.parseLong(uuid));
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @GetMapping(path="/health")
    public ResponseEntity<String> userInfo () {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // dump auth-status
    @GetMapping(path="/authentication-status")
    public ResponseEntity<String> authStatus () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout
    @PostMapping(path="/logout")
    public ResponseEntity<String> logout () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump logout-everywhere
    @PostMapping(path="/logout-everywhere")
    public ResponseEntity<String> logoutEverywhere () { return new ResponseEntity<>(HttpStatus.OK); }

    // dump session-info
    @GetMapping(path="/session-info")
    public ResponseEntity<String> sessionInfo () { return new ResponseEntity<>(HttpStatus.OK); }

}