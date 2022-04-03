package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IOrderService;
public class IOrderServiceImpl implements IOrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<Map<String,Object>> queryOrderList(Page page,int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryOrderList"))
    .queryParam("page",page)
    .queryParam("userId",userId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public Map<String,List<Map<String,Object>>> queryOrderDetailList(String orderId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryOrderDetailList"))
    .queryParam("orderId",orderId)
;  Map<String,List<Map<String,Object>>> aux = restTemplate.getForObject(builder.toUriString(), Map<String,List<Map<String,Object>>>.class);

 return aux;
}


public List<Map<String,Object>> queryMOrderDetailList(String orderId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryMOrderDetailList"))
    .queryParam("orderId",orderId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}