package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceType setAssigner(User assigner,Long id93GK){
 ResourceType aux = restTemplate.getForObject("http://9/ResourceType/{id}/User/setAssigner?'assigner'=assigner&'id93GK'=id93GK',",ResourceType.class,id93GK);
return aux;
}


public User getAssigner(Long id93GK){
 User aux = restTemplate.getForObject("http://9/ResourceType/{id}/User/getAssigner",User.class,id93GK);
return aux;
}


}