package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.OutStorageRepository;
public class OutStorageRepositoryImpl implements OutStorageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


}