package com.gnwoo.userservice.data.repo;

import com.gnwoo.userservice.data.table.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, String>{
    List<User> findByUsername(String username);

//    @Query(value = "SELECT hashed_password FROM auth WHERE uuid=?1", nativeQuery = true)
//    List<String> findHashedPassword(Long uuid);
//
//    @Query(value = "UPDATE auth SET hashed_password=?2 WHERE uuid=?1", nativeQuery = true)
//    void updateHashedPassword(Long uuid, String hashed_password);
}
