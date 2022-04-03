package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.ShoppingCartService;
public class ShoppingCartServiceImpl implements ShoppingCartService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}