package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SurveyService;
public class SurveyServiceImpl implements SurveyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}