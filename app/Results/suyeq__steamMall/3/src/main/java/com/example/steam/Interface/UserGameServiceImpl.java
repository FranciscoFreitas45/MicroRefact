package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.UserGameService;
public class UserGameServiceImpl implements UserGameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


}