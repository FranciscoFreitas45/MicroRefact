package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.UserDTO;
public class UserDTOImpl implements UserDTO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}