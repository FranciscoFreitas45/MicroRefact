package com.tech.services.interfaces;
 import com.tech.models.entities.user.User;
import javax.transaction.Transactional;
import java.util.List;
public interface IUserService {


@Transactional
public List<User> getAllUsers()
;

@Transactional
public void modifyUser(User modifiedUser)
;

@Transactional
public User getLastRecord()
;

@Transactional
public User getUserById(Long id)
;

@Transactional
public boolean validateUser(String username,String password)
;

@Transactional
public User getUserByUsername(String username)
;

@Transactional
public void updateUserRoom(boolean hasRoom,Long userid)
;

@Transactional
public void addUser(User user)
;

@Transactional
public long getNextID()
;

@Transactional
public boolean checkUsername(String username)
;

@Transactional
public long getCount()
;

}