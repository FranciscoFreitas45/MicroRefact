package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ClassesService;
public class ClassesServiceImpl implements ClassesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}