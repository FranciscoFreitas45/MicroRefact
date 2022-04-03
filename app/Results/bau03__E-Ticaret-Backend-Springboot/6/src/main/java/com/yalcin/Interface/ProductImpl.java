package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.Product;
public class ProductImpl implements Product{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}