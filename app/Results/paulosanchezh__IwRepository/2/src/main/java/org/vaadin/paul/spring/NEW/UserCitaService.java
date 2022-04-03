package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.UserRepository;
import org.vaadin.paul.spring.entities.User;
@Service
public class UserCitaService {

@Autowired
 private UserRepository userrepository;


public User getPaciente(int id){
return userrepository.getPaciente(id);
}


public void setPaciente(int id,User user){
userrepository.setPaciente(id,user);
}


}