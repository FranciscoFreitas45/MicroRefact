package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


}