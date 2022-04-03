package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IChatroomWhitelistService;
public class IChatroomWhitelistServiceImpl implements IChatroomWhitelistService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


}