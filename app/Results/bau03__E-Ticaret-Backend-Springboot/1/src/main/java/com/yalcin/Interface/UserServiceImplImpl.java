package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.UserServiceImpl;
public class UserServiceImplImpl implements UserServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}