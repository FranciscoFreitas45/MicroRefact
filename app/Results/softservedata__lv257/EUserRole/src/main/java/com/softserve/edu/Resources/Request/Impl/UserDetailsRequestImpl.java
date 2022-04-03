package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.UserDetails;
import com.softserve.edu.Resources.Request.UserDetailsRequest;
public class UserDetailsRequestImpl implements UserDetailsRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User setUserDetails(UserDetails userDetails,Long idO4D4){
 User aux = restTemplate.getForObject("http://1/User/{id}/UserDetails/setUserDetails?'userDetails'=userDetails&'idO4D4'=idO4D4',",User.class,idO4D4);
return aux;
}


public UserDetails getUserDetails(Long idO4D4){
 UserDetails aux = restTemplate.getForObject("http://1/User/{id}/UserDetails/getUserDetails",UserDetails.class,idO4D4);
return aux;
}


}