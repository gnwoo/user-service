package com.gnwoo.authservice.data.dto;

import com.gnwoo.authservice.data.table.Auth;

public class AuthDTO {
    private Long uuid;
    private String username;
    private String display_name;
    private String email;
    private String JWT_token;

    public AuthDTO() { }

    public AuthDTO(Auth auth) {
        this.uuid = auth.getUuid();
        this.username = auth.getUsername();
        this.display_name = auth.getDisplayName();
        this.email = auth.getEmail();
    }

    public Long getUuid() { return uuid; }

    public void setUuid(Long uuid) { this.uuid = uuid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDisplayName() { return display_name; }

    public void setDisplayName(String display_name) { this.display_name = display_name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
