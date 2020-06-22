package com.gnwoo.authservice.requestTemplate;

public class ChangePasswordRequest {
    private String username;
    private String passcode;
    private String newPassword;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPasscode() { return passcode; }

    public void setPasscode(String passcode) { this.passcode = passcode; }

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String new_password) { this.newPassword = new_password; }
}
