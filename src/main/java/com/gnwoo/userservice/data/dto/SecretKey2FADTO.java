package com.gnwoo.userservice.data.dto;

public class SecretKey2FADTO {
    private String secretKey2FA;

    public SecretKey2FADTO() { }

    public SecretKey2FADTO(String secretKey2FA) {
        this.secretKey2FA = secretKey2FA;
    }

    public void setSecretKey2FA(String secretKey2FA) {
        this.secretKey2FA = secretKey2FA;
    }

    public String getSecretKey2FA() {
        return this.secretKey2FA;
    }
}
