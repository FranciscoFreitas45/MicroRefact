package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ContragentsServiceImpl;
public class ContragentsServiceImplImpl implements ContragentsServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<ContragentsEntity> findAllContragents(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllContragents"))
;  List<ContragentsEntity> aux = restTemplate.getForObject(builder.toUriString(), List<ContragentsEntity>.class);

 return aux;
}


public ContragentsEntity loadContragentByContragentID(Integer contragentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadContragentByContragentID"))
    .queryParam("contragentId",contragentId)
;  ContragentsEntity aux = restTemplate.getForObject(builder.toUriString(), ContragentsEntity.class);

 return aux;
}


}