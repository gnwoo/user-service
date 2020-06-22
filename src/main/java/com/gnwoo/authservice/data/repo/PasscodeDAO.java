package com.gnwoo.authservice.data.repo;

public interface PasscodeDAO {
    public Boolean savePasscode(Long uuid, String passcode) ;
    public String findByUuid(Long uuid) ;
}
