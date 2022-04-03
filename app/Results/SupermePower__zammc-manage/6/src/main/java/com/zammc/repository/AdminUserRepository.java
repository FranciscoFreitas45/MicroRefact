package com.zammc.repository;
 import com.zammc.domain.user.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Long>{


@Query(value = "select count(user) from AdminUserEntity user where user.userName = :#{#request.userName}")
public int queryUserCount(AdminUserEntity request)
;

public AdminUserEntity findByUserNameAndPassWord(String userName,String passWord)
;

}