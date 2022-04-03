package com.qidian.hcm.module.center.repository;
 import com.qidian.hcm.module.center.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>{


public User findByUsername(String username)
;

public Optional<User> findByPhone(String phone)
;

public User findByPhoneOrEmail(String phone,String email)
;

public void deleteAllByGroupId(Long groupId)
;

}