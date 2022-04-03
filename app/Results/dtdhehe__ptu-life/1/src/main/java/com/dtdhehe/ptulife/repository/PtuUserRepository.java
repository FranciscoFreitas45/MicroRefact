package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.PtuUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PtuUserRepository extends JpaRepository<PtuUser, String>{


public List<PtuUser> findByUserStatus(Integer userStatus)
;

public List<PtuUser> findByUserSex(Integer userSex)
;

public PtuUser findByUserNameAndUserPwd(String userName,String userPwd)
;

}