package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.PropertyNameType;
public class PropertyNameTypeImpl implements PropertyNameType{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}