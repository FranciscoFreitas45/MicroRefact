package com.example.demo.Dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.Users;
public interface UserDAO extends JpaRepository<Users, String>{


@Query("SELECT u FROM Users u WHERE u.username=:username")
public Users findByUsername(String username)
;

}