package com.ITBlog.ITBlogSiteBackEnd.Repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ITBlog.ITBlogSiteBackEnd.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public User findByNameAndPassword(String name,String password)
;

@Modifying
@Query(value = "UPDATE User user SET user.type=2 WHERE user.userId=:userId")
public int closeAccount(long userId)
;

@Modifying
@Query(value = "UPDATE User user SET user.password=:password WHERE user.userId=:userId")
public int updatePassword(String password,long userId)
;

@Modifying
@Query(value = "UPDATE User user SET user.blogNum=(user.blogNum+1) WHERE user.userId=:userId")
public int addBlogNum(long userId)
;

@Modifying
@Query(value = "UPDATE User user SET user.age=:age WHERE user.userId=:userId")
public int updateAge(int age,long userId)
;

public User findByUserId(long userId)
;

@Modifying
@Query(value = "UPDATE User user SET user.phone=:phone WHERE user.userId=:userId")
public int updatePhone(long phone,long userId)
;

}