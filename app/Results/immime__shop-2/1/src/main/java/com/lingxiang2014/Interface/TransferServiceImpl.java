package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.TransferService;
public class TransferServiceImpl implements TransferService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<Transfer> findPage(Member fromMember,Member toMember,Method method,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPage"))
    .queryParam("fromMember",fromMember)
    .queryParam("toMember",toMember)
    .queryParam("method",method)
    .queryParam("pageable",pageable)
;  Page<Transfer> aux = restTemplate.getForObject(builder.toUriString(), Page<Transfer>.class);

 return aux;
}


}