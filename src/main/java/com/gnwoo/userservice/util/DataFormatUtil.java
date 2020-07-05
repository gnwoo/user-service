package com.gnwoo.userservice.util;

import java.util.regex.Pattern;

public class DataFormatUtil {
    public DataFormatUtil() {}

    public boolean checkUsernameFormat() {
        return true;
    }

    public boolean checkDisplayNameFormat(String display_name) {
        return display_name.length() <= 30;
    }

    public boolean checkEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean checkPasswordFormat() {
        return true;
    }
}
