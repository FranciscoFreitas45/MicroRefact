package com.uec.imonitor.user.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.UserEntity;
public interface IUserService {


public boolean updatePassword(Integer innerid,String oldPassword,String newPassword)
;

public UserEntity findByUserName(String userName)
;

public UserEntity addUser(UserEntity user)
;

public List<UserEntity> listUsers()
;

public UserEntity findById(Integer id)
;

public void deleteUser(Integer id)
;

public UserEntity updateUser(UserEntity user)
;

}