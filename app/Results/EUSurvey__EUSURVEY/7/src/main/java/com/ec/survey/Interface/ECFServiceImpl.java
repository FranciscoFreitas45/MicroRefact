package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ECFService;
public class ECFServiceImpl implements ECFService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


}