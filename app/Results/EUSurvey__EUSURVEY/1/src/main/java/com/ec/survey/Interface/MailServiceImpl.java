package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.MailService;
public class MailServiceImpl implements MailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}