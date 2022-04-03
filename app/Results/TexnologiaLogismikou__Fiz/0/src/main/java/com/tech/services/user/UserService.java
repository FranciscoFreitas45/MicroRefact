package com.tech.services.user;
 import com.tech.services.interfaces.IUserService;
import com.tech.models.entities.user.User;
import com.tech.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
@Service
public class UserService implements IUserService{

@Autowired
 private  IUserRepository repository;


@Override
@Transactional
public List<User> getAllUsers(){
    return repository.findAll();
}


@Override
@Transactional
public void modifyUser(User modifiedUser){
    repository.setUserInfoById(modifiedUser.getUsername(), modifiedUser.getPassword(), modifiedUser.isEnabled(), modifiedUser.hasRoom(), modifiedUser.getId());
}


@Override
@Transactional
public User getLastRecord(){
    Long i = getCount();
    return repository.getOne(i);
}


@Override
@Transactional
public User getUserById(Long id){
    return repository.findByUserid(id);
}


@Override
@Transactional
public boolean validateUser(String username,String password){
    User u = repository.findByUsernameAndPassword(username, password);
    return u != null;
}


@Override
@Transactional
public User getUserByUsername(String username){
    return repository.findByUsername(username);
}


@Override
@Transactional
public void updateUserRoom(boolean hasRoom,Long userid){
    repository.setUserRoom(hasRoom, userid);
}


@Override
@Transactional
public void addUser(User user){
    repository.save(user);
}


@Override
@Transactional
public long getNextID(){
    Long i = getCount();
    Long x = repository.getOne(i).getId();
    return x + 1L;
}


@Override
@Transactional
public boolean checkUsername(String username){
    User user = repository.findByUsername(username);
    return user != null;
}


@Override
@Transactional
public long getCount(){
    return repository.count();
}


}