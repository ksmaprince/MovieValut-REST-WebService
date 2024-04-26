package com.khun.movievault.repository;

import com.khun.movievault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
//    Optional<User> getUserByEmailPassword(String email);
    @Query(value = "SELECT * FROM res_user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
