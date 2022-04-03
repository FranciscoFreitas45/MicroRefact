package com.example.smartkitchenbackend.repositories.user;
 import com.example.smartkitchenbackend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class DefaultUserRepository implements UserRepository{

 private  UserJPARepository userJPARepository;


@Override
public User findByUsernameOrEmail(String username,String email){
    return userJPARepository.findByUsernameOrEmail(username, email).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
}


@Override
public User findById(long userId){
    return userJPARepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));
}


@Override
public void save(User user){
    userJPARepository.save(user);
}


@Override
public Boolean existsByUsername(String username){
    return userJPARepository.existsByUsername(username);
}


@Override
public Boolean existsByEmail(String email){
    return userJPARepository.existsByEmail(email);
}


@Override
public List<User> findAll(){
    return userJPARepository.findAll();
}


@Override
public User getReference(long userId){
    return userJPARepository.getOne(userId);
}


}