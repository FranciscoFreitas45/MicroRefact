package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.BatmanRecordRepository;
public class BatmanRecordRepositoryImpl implements BatmanRecordRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<BatmanRecord> findByUserId(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("userId",userId)
;  List<BatmanRecord> aux = restTemplate.getForObject(builder.toUriString(), List<BatmanRecord>.class);

 return aux;
}


}