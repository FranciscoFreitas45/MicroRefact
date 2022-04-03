package com.puffride.demo.dao;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.puffride.demo.entity.User;
import com.puffride.demo.repository.UserRepository;
@Service
@Transactional
public class UserDao {

@Autowired
 private UserRepository userRepository;


public User findOne(Long id){
    return userRepository.findById(id).orElse(null);
}


public User save(User entity){
    return userRepository.save(entity);
}


public void deleteAll(List<User> entityList){
    userRepository.deleteAll();
}


public List<User> findAll(){
    return userRepository.findAll();
}


public void delete(User entity){
    userRepository.delete(entity);
}


}