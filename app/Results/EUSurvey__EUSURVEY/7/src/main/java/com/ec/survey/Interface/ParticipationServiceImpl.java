package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ParticipationService;
public class ParticipationServiceImpl implements ParticipationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}