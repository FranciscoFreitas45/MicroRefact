package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.IInStorageService;
public class IInStorageServiceImpl implements IInStorageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void save(InStorage inStorage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("inStorage",inStorage)
;
  restTemplate.put(builder.toUriString(), null);
}


}