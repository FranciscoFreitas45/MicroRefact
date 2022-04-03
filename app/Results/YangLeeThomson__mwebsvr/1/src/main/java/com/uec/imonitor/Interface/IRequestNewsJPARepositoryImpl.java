package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IRequestNewsJPARepository;
public class IRequestNewsJPARepositoryImpl implements IRequestNewsJPARepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}