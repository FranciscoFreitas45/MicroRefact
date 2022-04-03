package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysArea;
public class SysAreaImpl implements SysArea{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}