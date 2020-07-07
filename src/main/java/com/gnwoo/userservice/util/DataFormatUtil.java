package com.gnwoo.userservice.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class DataFormatUtil {
    public DataFormatUtil() {}

    public void checkUsernameFormat(String username) throws IllegalArgumentException{
        if(!(username.length() >= 6 && username.length() <= 20))
            throw new IllegalArgumentException("username must be 6–20 characters long");
        String username_regex = "^[a-zA-Z0-9]+$";
        if(!Pattern.compile(username_regex).matcher(username).matches())
            throw new IllegalArgumentException("usernames can only contain letters (a-z) and numbers (0-9)");
    }

    public void checkDisplayNameFormat(String display_name) throws IllegalArgumentException{
        if(!(display_name.length() >= 2 && display_name.length() <= 30))
            throw new IllegalArgumentException("display name must be 2–30 characters long");
    }

    public void checkEmailFormat(String email) throws IllegalArgumentException{
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches())
            throw new IllegalArgumentException("invalid email format");
    }

    public void checkPasswordFormat(String password) throws IllegalArgumentException{
        String password_regex = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)" +
                "(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$";
        if(!Pattern.compile(password_regex).matcher(password).matches())
            throw new IllegalArgumentException("password must contain a lower case letter, a uppercase letter, " +
                    "a number and a special character");
    }

    public void checkPasscodeFormat(String passcode) throws IllegalArgumentException{
        try{
            Integer.parseInt(passcode);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("passcode must be a number");
        }
    }
}
