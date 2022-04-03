package com.csquard.mregister.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.csquard.mregister.Interface.AsmRepository;
public class AsmRepositoryImpl implements AsmRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}