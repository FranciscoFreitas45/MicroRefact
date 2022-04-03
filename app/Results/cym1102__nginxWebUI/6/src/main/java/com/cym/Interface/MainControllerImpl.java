package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.MainController;
public class MainControllerImpl implements MainController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}