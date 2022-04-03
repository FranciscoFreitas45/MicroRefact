package com.ITBlog.DTO;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ITBlog.ITBlogSiteBackEnd.Entity.User;
import com.ITBlog.ITBlogSiteBackEnd.Repository.UserRepository;
public class UserService {

 private  UserRepository userRepository;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

/**
 * 自动注入Repository的构造函数
 * @param userRepository
 */
@Autowired
public UserService(UserRepository userRepository) {
    this.setUserRepository(userRepository);
}
@Transactional(propagation = Propagation.REQUIRED)
public int getTypeByUserId(long userId){
    return this.userRepository.findByUserId(userId).getType();
}


@Transactional(propagation = Propagation.REQUIRED)
public User getUserByNameAndPassword(String name,String password){
    User user = this.userRepository.findByNameAndPassword(name, password);
    return user;
}


public UserRepository getUserRepository(){
    return userRepository;
}


@Transactional(propagation = Propagation.REQUIRED)
public User getUserByUserId(long userId){
    User user = this.userRepository.findByUserId(userId);
    return user;
}


@Transactional(propagation = Propagation.REQUIRED)
public int updatePassword(long userId,String password){
    int effectedNum = this.userRepository.updatePassword(password, userId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updatePassword"))

.queryParam("userId",userId)
.queryParam("password",password)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


@Transactional(propagation = Propagation.REQUIRED)
public int deleteUser(long userId){
    this.userRepository.deleteById(userId);
    return 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUser"))

.queryParam("userId",userId)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


@Transactional(propagation = Propagation.REQUIRED)
public int saveUser(String name,int gender,int age,String password,long phone){
    // Type是1表示普通用户
    User user = new User(name, gender, age, password, 0, 1, phone);
    // saveAndFlush 可以返回实体
    User userTemp = this.userRepository.saveAndFlush(user);
    if (userTemp == null) {
        return 1;
    } else if (userTemp.getUserId() == 0) {
        return 1;
    } else {
        return 0;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveUser"))

.queryParam("name",name)
.queryParam("gender",gender)
.queryParam("age",age)
.queryParam("password",password)
.queryParam("phone",phone)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


@Transactional(propagation = Propagation.REQUIRED)
public int closeUserAccount(long userId){
    int effectedNum = this.userRepository.closeAccount(userId);
    if (effectedNum == 0) {
        return 1;
    } else {
        return 0;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/closeUserAccount"))

.queryParam("userId",userId)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}