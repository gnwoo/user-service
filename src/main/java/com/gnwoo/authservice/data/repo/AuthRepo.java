package com.gnwoo.authservice.data.repo;

import com.gnwoo.authservice.data.table.Auth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthRepo extends CrudRepository<Auth, String>{
    List<Auth> findByUsername(String username);

    @Query(value = "SELECT hashed_password FROM auth WHERE uuid=?1", nativeQuery = true)
    List<String> findHashedPassword(Long uuid);

    @Query(value = "UPDATE auth SET hashed_password=?2 WHERE uuid=?1", nativeQuery = true)
    void updateHashedPassword(Long uuid, String hashed_password);
}
