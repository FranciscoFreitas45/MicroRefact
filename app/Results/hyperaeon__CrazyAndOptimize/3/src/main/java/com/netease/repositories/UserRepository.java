package com.netease.repositories;
 import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.netease.domain.User;
public interface UserRepository extends CrudRepository<User, Integer>{


public User findById(Integer id)
;

public User save(User user)
;

public User findByName(String name)
;

public User getByLoginName(String loginName)
;

public List<User> findByCreateUser(String createUser)
;

public List<User> findAll()
;

@Transactional
@Modifying
@Query("update User set report_status=:report_status where login_name=:login_name")
public Integer updateByLoginName(Integer report_status,String login_name)
;

public User findByMobileNumber(BigInteger mobileNumber)
;

public void setCredential_number(Integer id,String credential_number);

public void setCredential_type(Integer id,Integer credential_type);

public void setReport_status(Integer id,Integer report_status);

public void setIs_married(Integer id,Integer is_married);

}