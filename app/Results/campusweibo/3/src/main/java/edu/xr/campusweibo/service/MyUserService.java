package edu.xr.campusweibo.service;
 import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.repository.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MyUserService {

 private  Logger logger;

@Autowired
 private  MyUserRepository myUserRepository;


public MyUser getUserById(Long id){
    return myUserRepository.findOne(id);
}


public MyUser getUserBySchoolcode(String schoolcode){
    return myUserRepository.findBySchoolcode(schoolcode);
}


public void saveUser(MyUser myUser){
    if (myUser.getId() == null) {
        logger.info("开始创建用户=" + myUser.getSchoolcode());
        MyUser regUser = new MyUser();
        BeanUtils.copyProperties(myUser, regUser);
        myUserRepository.save(regUser);
    } else {
        logger.info("更新用户信息=" + myUser.getSchoolcode());
        myUserRepository.save(myUser);
    }
}


}