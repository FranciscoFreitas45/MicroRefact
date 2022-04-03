package io.swagger.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.DTO.Address;
import io.swagger.Request.AddressRequest;
public class AddressRequestImpl implements AddressRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Address> getAddress(Long id){
 List<Address> aux = restTemplate.getForObject("http://1/User/{id}/Address/getAddress",List<Address>.class,id);
return aux;
}


public void setAddress(List<Address> address,Long id){
 restTemplate.put("http://1/User/{id}/Address/setAddress",address,id);
 return ;
}


}