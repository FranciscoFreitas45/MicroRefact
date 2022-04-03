package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.UserService;
import java.util.*;
import com.sda.inTeams.model.*;
import com.sda.inTeams.DTO.*;
 import com.sda.inTeams.exception.InvalidOperation;


public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";



public User getByIdOrThrow(long userId) throws InvalidOperation {
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByIdOrThrow"))
    .queryParam("userId",userId)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
} 
}