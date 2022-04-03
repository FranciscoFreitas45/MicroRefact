package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public StorageIoDetail lackPart(Integer ioDetailId,Integer actualAmt,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lackPart"))
    .queryParam("ioDetailId",ioDetailId)
    .queryParam("actualAmt",actualAmt)
    .queryParam("operator",operator)
;  StorageIoDetail aux = restTemplate.getForObject(builder.toUriString(), StorageIoDetail.class);

 return aux;
}


public StorageIoDetail lackAll(Integer ioDetailId,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lackAll"))
    .queryParam("ioDetailId",ioDetailId)
    .queryParam("operator",operator)
;  StorageIoDetail aux = restTemplate.getForObject(builder.toUriString(), StorageIoDetail.class);

 return aux;
}


public void finishSend(Integer repoId,Integer batchId,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/finishSend"))
    .queryParam("repoId",repoId)
    .queryParam("batchId",batchId)
    .queryParam("operator",operator)
;
  restTemplate.put(builder.toUriString(), null);
}


}