package com.cg.oms.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.Interface.Address;
public class AddressImpl implements Address{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}