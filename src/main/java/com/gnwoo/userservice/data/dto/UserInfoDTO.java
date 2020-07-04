package com.gnwoo.userservice.data.dto;

import com.gnwoo.userservice.data.table.User;

public class UserInfoDTO {
    private Long uuid;
    private String username;
    private String displayName;
    private String email;
    private Boolean is2FA;

    public UserInfoDTO() { }

    public UserInfoDTO(User user) {
        this.uuid = user.getUuid();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.is2FA = user.getIs2FA();
    }

    public Long getUuid() { return uuid; }

    public void setUuid(Long uuid) { this.uuid = uuid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String display_name) { this.displayName = display_name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Boolean getIs2FA() { return is2FA; }

    public void setIs2FA(boolean is2FA) { this.is2FA = is2FA; }
}
