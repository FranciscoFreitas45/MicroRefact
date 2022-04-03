package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.ApplyOutPutRepository;
public class ApplyOutPutRepositoryImpl implements ApplyOutPutRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<ApplyOutPut> findAllByOutCode(String outCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByOutCode"))
    .queryParam("outCode",outCode)
;  List<ApplyOutPut> aux = restTemplate.getForObject(builder.toUriString(), List<ApplyOutPut>.class);

 return aux;
}


}