package org.vaadin.paul.spring.services;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vaadin.paul.spring.entities.User;
import org.vaadin.paul.spring.repositories.UserRepository;
import org.vaadin.paul.spring.Interface.UserRepository;
@Service
public class UserService implements UserDetailsService{

 private  UserRepository repo;

 private  PasswordEncoder passwordEncoder;

@Autowired
public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
    super();
    this.repo = repo;
    this.passwordEncoder = passwordEncoder;
}
public User loadUserByUsername(String username){
    User user = repo.findByusername(username);
    if (user == null) {
        throw new UsernameNotFoundException(username);
    }
    return user;
}


}