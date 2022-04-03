package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.PropertyFilter;
public class PropertyFilterImpl implements PropertyFilter{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://17";


}