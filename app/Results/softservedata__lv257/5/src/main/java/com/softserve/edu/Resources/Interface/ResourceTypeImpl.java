package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.ResourceType;
public class ResourceTypeImpl implements ResourceType{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}