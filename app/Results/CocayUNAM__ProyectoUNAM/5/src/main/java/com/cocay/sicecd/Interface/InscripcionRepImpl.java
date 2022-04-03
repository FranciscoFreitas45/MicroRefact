package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.InscripcionRep;
public class InscripcionRepImpl implements InscripcionRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}