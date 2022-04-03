package edu.xr.campusweibo.service;
 import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.repository.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoginService {

 private  Logger logger;

@Autowired
 private  MyUserRepository myUserRepository;


public MyUser getUserById(Long id){
    return myUserRepository.findById(id);
}


public MyUser findBySchoolcodeAndPassword(String schoolcode,String password){
    return myUserRepository.findBySchoolcodeAndPassword(schoolcode, password);
}


}