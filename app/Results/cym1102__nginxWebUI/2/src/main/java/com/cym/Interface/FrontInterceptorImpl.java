package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.FrontInterceptor;
public class FrontInterceptorImpl implements FrontInterceptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}