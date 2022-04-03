package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IEsIndexService;
public class IEsIndexServiceImpl implements IEsIndexService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}