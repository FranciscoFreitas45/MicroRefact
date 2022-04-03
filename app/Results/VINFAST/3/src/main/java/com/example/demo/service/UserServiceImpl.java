package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.UserDAO;
import com.example.demo.entity.Users;
@Service
public class UserServiceImpl implements UserService{

@Autowired
 private UserDAO dao;


@Override
public boolean checknull(String username){
    Optional<Users> optionalUsers = findById(username);
    if (optionalUsers.isPresent() && optionalUsers.get().getUsername().equals(username)) {
        return true;
    }
    return false;
}


@Override
public List<Users> saveAll(List<Users> entities){
    return dao.saveAll(entities);
}


@Override
public Users save(Users entity){
    return dao.save(entity);
}


@Override
public long count(){
    return dao.count();
}


@Override
public void deleteAll(){
    dao.deleteAll();
}


@Override
public void deleteAllById(List<String> ids){
    dao.deleteAllById(ids);
}


@Override
public List<Users> findAll(Sort sort){
    return (List<Users>) dao.findAll(sort);
}


@Override
public void delete(Users entity){
    dao.delete(entity);
}


@Override
public boolean checkLogin(String username,String password){
    Optional<Users> optionalUsers = findById(username);
    if (optionalUsers.isPresent() && optionalUsers.get().getPassword().equals(password)) {
        return true;
    }
    return false;
}


@Override
public boolean existsById(String id){
    return dao.existsById(id);
}


@Override
public List<Users> findAllById(List<String> ids){
    return (List<Users>) dao.findAllById(ids);
}


@Override
public Users findByUsername(String usersname){
    Users user = dao.findByUsername(usersname);
    return user;
}


@Override
public Optional<Users> findById(String id){
    return dao.findById(id);
}


@Override
public void deleteById(String id){
    dao.deleteById(id);
}


}