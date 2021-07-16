package com.pl.edu.wat.wcy.pz.restaurantServer.service;

import com.DTO.User;
import com.Interface.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class UserService {
    @Autowired
 private UserRepository userRepository;
 private  PasswordEncoder encoder;


public Optional<User> getUserById(Long id){
    Optional<User> user = (Optional<User>) userRepository.findById(id);
    if (user.isPresent()) {
        return user;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }
}

/*
public List<Reservation> getUserReservations(Long id){
    Optional<User> user = getUserById(id);
    return user.get().getReservations();
}
*/

public List<User> getUsers(){
    return (List<User>) userRepository.findAll();
}


public void addUser(User user){
    List<User> userList = (List<User>) userRepository.findAll();
    if (userList.stream().map(User::getMail).anyMatch(user.getMail()::equals)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with this email already exists.");
    } else {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}


public void updateUser(Long id,User user){
    Optional<User> oldUser = (Optional<User>) userRepository.findById(id);
    if (!oldUser.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    else {
        user.setUserId(id);
        userRepository.save(user);
    }
}

/*
public void deleteUserById(Long id){
    if (userRepository.findById(id).isPresent()) {
        userRepository.deleteById(id);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }
}
*/

}