package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.BallerRecordRepository;
public class BallerRecordRepositoryImpl implements BallerRecordRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<BallerRecord> findByUserId(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("userId",userId)
;  List<BallerRecord> aux = restTemplate.getForObject(builder.toUriString(), List<BallerRecord>.class);

 return aux;
}


}