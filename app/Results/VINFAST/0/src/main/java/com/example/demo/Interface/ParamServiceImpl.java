package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.ParamService;
public class ParamServiceImpl implements ParamService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}