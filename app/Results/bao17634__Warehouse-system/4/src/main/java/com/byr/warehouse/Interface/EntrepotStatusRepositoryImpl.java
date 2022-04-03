package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
public class EntrepotStatusRepositoryImpl implements EntrepotStatusRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}