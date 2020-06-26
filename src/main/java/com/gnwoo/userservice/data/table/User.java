package com.gnwoo.userservice.data.table;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uuid;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String displayName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String hashed_password;

    public User() { }

    public User(String username, String displayName, String email, String hashed_password) {
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.hashed_password = hashed_password;
    }

    public Long getUuid() { return uuid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getHashedPassword() { return hashed_password; }

    public void setHashedPassword(String hashed_password) { this.hashed_password = hashed_password; }
}