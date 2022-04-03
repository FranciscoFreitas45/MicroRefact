package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.LabelService;
public class LabelServiceImpl implements LabelService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public HotLabel save(HotLabel hotLabel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("hotLabel",hotLabel)
;  HotLabel aux = restTemplate.getForObject(builder.toUriString(), HotLabel.class);

 return aux;
}


}