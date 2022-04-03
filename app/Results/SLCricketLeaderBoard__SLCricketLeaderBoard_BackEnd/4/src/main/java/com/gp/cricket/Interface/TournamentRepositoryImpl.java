package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentRepository;
public class TournamentRepositoryImpl implements TournamentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


}