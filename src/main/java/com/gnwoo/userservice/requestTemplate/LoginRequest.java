package com.gnwoo.userservice.requestTemplate;

public class LoginRequest {
    private String username;
    private String password;
    private String passcode2FA;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getPasscode2FA() { return passcode2FA; }

    public void setPasscode2FA(String passcode2FA) { this.passcode2FA = passcode2FA; }
}
