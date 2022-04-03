package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long idXLHJ){
 User aux = restTemplate.getForObject("http://9/VerificationToken/{id}/User/getUser",User.class,idXLHJ);
return aux;
}


public VerificationToken setUser(User user,Long idXLHJ){
 VerificationToken aux = restTemplate.getForObject("http://9/VerificationToken/{id}/User/setUser?'user'=user&'idXLHJ'=idXLHJ',",VerificationToken.class,idXLHJ);
return aux;
}


}