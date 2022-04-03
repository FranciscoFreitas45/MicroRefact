package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseService;
public class DoPurchaseServiceImpl implements DoPurchaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<PurchaseDetail> findPurchaseDetailByBatchId(Integer batchId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPurchaseDetailByBatchId"))
    .queryParam("batchId",batchId)
;  List<PurchaseDetail> aux = restTemplate.getForObject(builder.toUriString(), List<PurchaseDetail>.class);

 return aux;
}


}