package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.RoleRepository;
public class RoleRepositoryImpl implements RoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}