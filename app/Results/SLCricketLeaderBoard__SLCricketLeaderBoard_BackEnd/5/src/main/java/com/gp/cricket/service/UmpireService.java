package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.config.security.JwtInMemoryUserDetailsService;
import com.gp.cricket.entity.Umpire;
import com.gp.cricket.entity.User;
import com.gp.cricket.repository.UmpireRepository;
@Service
public class UmpireService {

@Autowired
 private UmpireRepository umpireRepository;

@Autowired
 private UserService userService;

@Autowired
 private JwtInMemoryUserDetailsService jwtUser;


public List<Umpire> getAllUmpires(){
    System.out.println("Get all umpires here");
    return this.umpireRepository.findAll();
}


public Umpire saveUmpire(User user){
    Byte x = 1;
    Byte y = 3;
    user.setStatus(x);
    user.setRole(y);
    User tempUser = userService.registerUser(user);
    Umpire umpire = new Umpire(null, tempUser);
    return this.umpireRepository.save(umpire);
}


public List<Umpire> getAvailableUmpires(){
    return umpireRepository.getAvailableUmpires();
}


public Umpire getAvailableUmpire(Integer id){
    return umpireRepository.getAvailableUmpire(id);
}


}