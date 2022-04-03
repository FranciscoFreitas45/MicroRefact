package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.AnswerService;
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}