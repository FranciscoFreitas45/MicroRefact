package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.EstadoRep;
public class EstadoRepImpl implements EstadoRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}