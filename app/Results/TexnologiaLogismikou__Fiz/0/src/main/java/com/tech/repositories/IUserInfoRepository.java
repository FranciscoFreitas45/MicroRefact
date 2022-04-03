package com.tech.repositories;
 import com.tech.models.entities.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long>{


@Modifying
@Query("update UserInfo u set u.firstname = ?1, u.last_name = ?2, u.birthday = ?3, " + "u.email = ?4, u.status = ?5, u.profile_photo = ?6, u.hometown = ?7 where u.userid = ?8")
public void setUserInfoById(String first_name,String last_name,Date birthday,String email,String status,String profile_photo,String hometown,Long userid)
;

public List<UserInfo> findByFirstName(String first_name)
;

public UserInfo findByUserid(Long userid)
;

public List<UserInfo> findByLastName(String last_name)
;

public List<UserInfo> findByBirthDay(Date birthday)
;

public UserInfo findByEmail(String email)
;

public List<UserInfo> findByHomeTown(String hometown)
;

}