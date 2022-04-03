package com.byr.warehouse.dao;
 import com.byr.warehouse.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public User findUserByid(Long id)
;

public List<User> findUserByusername(String username)
;

}