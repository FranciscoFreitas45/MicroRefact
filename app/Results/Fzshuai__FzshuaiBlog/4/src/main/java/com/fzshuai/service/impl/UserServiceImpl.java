package com.fzshuai.service.impl;
 import com.fzshuai.dao.UserRepository;
import com.fzshuai.po.User;
import com.fzshuai.service.UserService;
import com.fzshuai.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{

@Autowired
 private  UserRepository userRepository;


@Override
public User checkUser(String username,String password){
    // 控制台显示信息
    System.out.println(username + " " + password);
    // 验证
    User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    return user;
}


}