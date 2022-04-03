package com.cg.oms.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.Interface.Order;
public class OrderImpl implements Order{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}