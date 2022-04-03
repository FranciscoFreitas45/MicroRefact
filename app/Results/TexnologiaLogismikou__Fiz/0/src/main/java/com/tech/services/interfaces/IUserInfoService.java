package com.tech.services.interfaces;
 import com.tech.models.entities.user.UserInfo;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
public interface IUserInfoService {


@Transactional
public boolean checkMail(String mail)
;

@Transactional
public UserInfo getUserInfoByUserId(Long userId)
;

@Transactional
public List<UserInfo> findByFirstName(String first_name)
;

@Transactional
public List<UserInfo> findByLastName(String last_name)
;

@Transactional
public List<UserInfo> findByBirthDay(Date birthday)
;

@Transactional
public void addUserInfo(UserInfo userInfo)
;

@Transactional
public void modifyUserInfo(UserInfo modifiedUser)
;

@Transactional
public UserInfo getUserInfoByEmail(String mail)
;

@Transactional
public List<UserInfo> findByHomeTown(String hometown)
;

}