package com.postapi.user.repository;



import com.postapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("select users from Users users where users.email=?1")
    Optional<Users> findByEmail(String email);

    @Query("select users from Users users where users.username=?1")
    Optional<Users> findByUsername(String username);

    @Query("select users from Users users where users.username=?1")
    Optional<Users> findByEmailForOtp(String email);

    @Query("select users from Users users where users.id=?1")
    Optional<Users> findByUserId(Long id);

    @Query("select users from Users users where users.username=?1 and password=?2")
    Optional<Users> loginRequest(String username, String password);

    @Query("select users from Users users where users.id=?1")
    Users findUserById(Long id);
}
