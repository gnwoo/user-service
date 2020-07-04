package com.gnwoo.userservice;

import com.gnwoo.userservice.data.dto.UserInfoDTO;
import com.gnwoo.userservice.data.repo.PasscodeRepo;
import com.gnwoo.userservice.data.repo.UserRepo;
import com.gnwoo.userservice.data.table.User;
import com.gnwoo.userservice.exception.DuplicateUsernameException;
import com.gnwoo.userservice.exception.NotFoundException;
import com.gnwoo.userservice.exception.Require2FAException;
import com.gnwoo.userservice.exception.UnauthorizedException;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

@Component
public class UserService {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasscodeRepo passcodeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    public UserService() {}

    public String handleSignUp(String username, String password, String display_name,
                                                                 String email, boolean is2FA)
            throws DuplicateUsernameException {
        String secretKey2FA = null;

        // check username duplicates
        if(!userRepo.findByUsername(username).isEmpty())
            throw new DuplicateUsernameException("username has been already registered");

        // otherwise, new user, hash the password
        String hashed_password = passwordEncoder.encode(password);

        User user = new User(username, display_name, email, hashed_password);
        // if user enables 2FA, generate a 2FA secret key
        if(is2FA)
        {
            final GoogleAuthenticatorKey key = gAuth.createCredentials();
            user.setSecretKey2FA(key.getKey());
            user.setIs2FA(true);
            secretKey2FA = key.getKey();
        }

        // save the user to the db
        userRepo.save(user);

        return secretKey2FA;
    }

    public UserInfoDTO handleLogin(String username, String password, String passcode_2FA) throws NotFoundException {
        // get user from db
        List<User> relations = userRepo.findByUsername(username);

        // invalid login: username does not exist
        if(relations.isEmpty())
            throw new UnauthorizedException("username does not exist");

        // otherwise, user found
        User user = relations.get(0);

        // valid login: username and password combination matched
        if(passwordEncoder.matches(password, user.getHashedPassword())) {
            // if user enables 2FA
            if(user.getIs2FA())
            {
                // tell frontend to let user input 2FA passcode
                if(passcode_2FA == null)
                    throw new Require2FAException("user enabled 2FA");
                // valid login: 2FA passcode matched
                else if(gAuth.authorize(user.getSecretKey2FA(), Integer.parseInt(passcode_2FA)))
                    return new UserInfoDTO(user);
                // invalid 2FA passcode
                else
                    throw new UnauthorizedException("invalid 2FA passcode");
            }
            // valid login: user does not enable 2FA
            return new UserInfoDTO(user);
        }
        // otherwise, invalid login
        throw new UnauthorizedException("invalid username or password");
    }

    public void verifyByEmailAddress(String username) throws NotFoundException {
        // get user from db
        List<User> relations = userRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            throw new NotFoundException("username does not exist");

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

    }

    public Long changePassword(String username, String passcode, String new_password) throws UnauthorizedException {
        // get user from db
        List<User> relations = userRepo.findByUsername(username);

        // invalid: username does not exist
        if(relations.isEmpty())
            throw new UnauthorizedException("username does not exist");

        // otherwise, user found
        User user = relations.get(0);
        Long uuid = user.getUuid();
        String saved_passcode = passcodeRepo.findByUuid(uuid);

        // valid passcode
        if(saved_passcode != null && saved_passcode.equals(passcode))
        {
            // update hashed password in db
            String new_hashed_password = passwordEncoder.encode(new_password);
            user.setHashedPassword(new_hashed_password);
            userRepo.save(user);

            // invalidate passcode
            passcodeRepo.deleteByUuid(uuid);

            return uuid;
        }
        // invalid passcode
        throw new UnauthorizedException("invalid passcode");
    }

    public String change2FAStatus(Long uuid) {
        // get uuid from session
        List<User> relations = userRepo.findByUuid(uuid);

        User user = relations.get(0);
        String secretKey2FA = null;
        // if user disables 2FA, enable it
        if(!user.getIs2FA())
        {
            user.setIs2FA(true);
            // generate 2FA secret key
            final GoogleAuthenticatorKey key = gAuth.createCredentials();
            user.setSecretKey2FA(key.getKey());
            secretKey2FA = key.getKey();
        }
        // otherwise, the user enables 2FA, disable it
        else
        {
            user.setIs2FA(false);
        }

        // save user's new 2FA status to db
        userRepo.save(user);

        // return
        return secretKey2FA;
    }

    public UserInfoDTO getUserInfo(Long uuid) {
        List<User> relations = userRepo.findByUuid(uuid);
        User user = relations.get(0);
        return new UserInfoDTO(user);
    }

}
