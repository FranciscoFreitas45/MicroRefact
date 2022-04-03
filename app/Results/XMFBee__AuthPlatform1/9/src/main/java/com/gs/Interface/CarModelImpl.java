package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.CarModel;
public class CarModelImpl implements CarModel{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}