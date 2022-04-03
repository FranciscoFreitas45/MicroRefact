package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Address;
import com.cg.oms.Request.AddressRequest;
public class AddressRequestImpl implements AddressRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Address getUserAddress(Integer addressId){
 Address aux = restTemplate.getForObject("http://localhost:8085/User/{id}/Address/getUserAddress",Address.class,addressId);
return aux;
}


public void setUserAddress(Address userAddress,Integer addressId){
 restTemplate.put("http://localhost:8085/User/{id}/Address/setUserAddress",userAddress,addressId);
 return ;
}


}