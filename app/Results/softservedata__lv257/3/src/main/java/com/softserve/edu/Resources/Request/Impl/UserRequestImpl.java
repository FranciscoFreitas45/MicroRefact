package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ResourceRequest setResourcesAdmin(User resourcesAdmin,Long idSND5){
 ResourceRequest aux = restTemplate.getForObject("http://9/ResourceRequest/{id}/User/setResourcesAdmin?'resourcesAdmin'=resourcesAdmin&'idSND5'=idSND5',",ResourceRequest.class,idSND5);
return aux;
}


public ResourceRequest setRegister(User register,Long idSND5){
 ResourceRequest aux = restTemplate.getForObject("http://9/ResourceRequest/{id}/User/setRegister?'register'=register&'idSND5'=idSND5',",ResourceRequest.class,idSND5);
return aux;
}


public User getRegister(Long idSND5){
 User aux = restTemplate.getForObject("http://9/ResourceRequest/{id}/User/getRegister",User.class,idSND5);
return aux;
}


public User getResourcesAdmin(Long idSND5){
 User aux = restTemplate.getForObject("http://9/ResourceRequest/{id}/User/getResourcesAdmin",User.class,idSND5);
return aux;
}


}