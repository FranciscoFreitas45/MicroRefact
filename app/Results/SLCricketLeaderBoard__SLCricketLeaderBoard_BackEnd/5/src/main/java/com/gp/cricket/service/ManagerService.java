package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.gp.cricket.config.security.JwtInMemoryUserDetailsService;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Manager;
import com.gp.cricket.entity.User;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.ManagerRepository;
import com.gp.cricket.repository.UserRepository;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class ManagerService {

@Autowired
 private ManagerRepository managerRepository;

@Autowired
 private UserService userService;

@Autowired
 private UserRepository userRepository;

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private JwtInMemoryUserDetailsService jwtUser;


public Manager getManager(Integer userId){
    if (userId != null && userRepository.existsById(userId)) {
        User user = userRepository.findByUserId(userId);
        return managerRepository.getManager(user);
    }
    return null;
}


public Manager saveManager(User user){
    // Byte x = 1;
    // user.setStatus(x);
    User tempUser = userService.signupUser(user);
    Manager manager = new Manager(null, tempUser);
    return this.managerRepository.save(manager);
}


public List<Manager> getAvailableManagers(){
    return managerRepository.getAvailableManagers();
}


public Club getManagerClub(Integer managerId){
    return clubRepository.findClubByManagerId(managerId);
}


public List<Manager> getAllManagers(){
    System.out.println("Get all managers here");
    return this.managerRepository.findAll();
}


public List<Manager> getRequestedManagers(){
    return managerRepository.getRequestedManagers();
}


public List<Manager> getAcceptedManagers(){
    return managerRepository.getAcceptedManagers();
}


}