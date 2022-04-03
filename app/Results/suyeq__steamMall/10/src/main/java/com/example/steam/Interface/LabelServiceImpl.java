package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.LabelService;
public class LabelServiceImpl implements LabelService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Label> findAllLabel(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllLabel"))
;  List<Label> aux = restTemplate.getForObject(builder.toUriString(), List<Label>.class);

 return aux;
}


}