package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.StorageService;
public class StorageServiceImpl implements StorageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void directInStorage(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/directInStorage"))
    .queryParam("repoId",repoId)
    .queryParam("skuId",skuId)
    .queryParam("amount",amount)
    .queryParam("posLabel",posLabel)
    .queryParam("operator",operator)
;
  restTemplate.put(builder.toUriString(), null);
}


public StorageIoDetail addInStorageDetail(Integer batchId,Integer skuId,Integer amount,String posLabel,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addInStorageDetail"))
    .queryParam("batchId",batchId)
    .queryParam("skuId",skuId)
    .queryParam("amount",amount)
    .queryParam("posLabel",posLabel)
    .queryParam("operator",operator)
;  StorageIoDetail aux = restTemplate.getForObject(builder.toUriString(), StorageIoDetail.class);

 return aux;
}


}