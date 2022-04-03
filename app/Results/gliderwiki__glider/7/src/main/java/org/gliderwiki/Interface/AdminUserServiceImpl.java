package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.AdminUserService;
public class AdminUserServiceImpl implements AdminUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}