package com.gnwoo.userservice.requestTemplate;

public class SignUpEmailVerificationRequest {
    private String email;
    private String passcode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
