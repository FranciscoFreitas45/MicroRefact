package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.InStorage;
import com.hmm.Request.InStorageRequest;
public class InStorageRequestImpl implements InStorageRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setInAll(InStorage inAll,String inStorageIdEAHF){
 restTemplate.put("http://5/DoSend/{id}/InStorage/setInAll",inAll,inStorageIdEAHF);
 return ;
}


public InStorage getInAll(String inStorageIdEAHF){
 InStorage aux = restTemplate.getForObject("http://5/DoSend/{id}/InStorage/getInAll",InStorage.class,inStorageIdEAHF);
return aux;
}


}