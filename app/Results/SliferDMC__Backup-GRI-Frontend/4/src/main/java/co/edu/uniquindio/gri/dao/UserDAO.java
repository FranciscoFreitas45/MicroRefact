package co.edu.uniquindio.gri.dao;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.User;
import co.edu.uniquindio.gri.repository.UserRepository;
@Service
public class UserDAO {

@Autowired
 private UserRepository userRepository;


public User findOne(String username){
    return userRepository.findOne(username);
}


}