package com.cg.hbm.service;
 import java.util.List;
import java.util.Optional;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IUserRepository;
@Service
public class IUserServiceImpl implements IUserService{

@Autowired
 private IUserRepository uDao;


@Override
public User removeUser(int user_id){
    // TODO Auto-generated method stub
    Optional<User> op = uDao.findById(user_id);
    if (op.isPresent()) {
        uDao.deleteById(user_id);
        return op.get();
    } else
        throw new UserNotFoundException("User with given Id doesn't exist.");
}


@Override
public User addUser(User user){
    // TODO Auto-generated method stub
    Optional<User> u = uDao.findById(user.getUser_id());
    if (u.isEmpty()) {
        return uDao.saveAndFlush(user);
    } else {
        throw new UserNotFoundException("User already exist");
    }
}


@Override
public User showUser(int user_id){
    // TODO Auto-generated method stub
    Optional<User> h = uDao.findById(user_id);
    if (h.isEmpty()) {
        throw new UserNotFoundException("Given UserId does not exist");
    }
    return h.get();
}


@Override
public User updateUser(int userId,User user){
    // TODO Auto-generated method stub
    Optional<User> h = uDao.findById(userId);
    if (h.isEmpty()) {
        throw new UserNotFoundException("User not found");
    } else
        uDao.save(user);
    return user;
}


@Override
public List<User> showAllUsers(){
    // TODO Auto-generated method stub
    List<User> h = uDao.findAll();
    if (h.isEmpty()) {
        throw new UserNotFoundException("User not found");
    }
    return h;
}


}