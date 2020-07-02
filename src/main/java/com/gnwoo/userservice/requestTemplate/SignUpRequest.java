package com.gnwoo.userservice.requestTemplate;

public class SignUpRequest {
    private String username;
    private String displayName;
    private String password;
    private String email;
    private Boolean is2FA;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIs2FA() {
        return is2FA;
    }

    public void setIs2FA(boolean is2FA) {
        this.is2FA = is2FA;
    }
}
