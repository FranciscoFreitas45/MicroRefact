package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Role;
import com.ipe.module.core.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface UserRepository extends CustomerRepository<User, String>{


@Query("select t.role from UserRole t where t.user.id=:userId")
public List<Role> getUserRole(String userId)
;

@Query("from Role t0 where t0.id not in(select t.role from UserRole t where t.user.id=:userId)")
public List<Role> getUserNotRole(String userId)
;

@Modifying
@Query("update User u set u.userPwd=:userPwd where u.id=:id and u.userPwd=:ouserPwd")
public int updatePwd(String id,String userPwd,String ouserPwd)
;

@Query("select t.role.id from UserRole t where t.user.id=:userId")
public List<String> getUserRoleIds(String userId)
;

@Query("from User u where u.userAccount=:account  and u.enabled='1'")
public List<User> findUserByAccount(String account)
;

@Query("from User u where u.userAccount=:account and u.userPwd=:pwd and u.enabled='1'")
public List<User> login(String account,String pwd)
;

}