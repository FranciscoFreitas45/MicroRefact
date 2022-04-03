package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.CarColor;
public class CarColorImpl implements CarColor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


}