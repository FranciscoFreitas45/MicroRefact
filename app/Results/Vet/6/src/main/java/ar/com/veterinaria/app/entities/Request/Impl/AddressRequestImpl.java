package ar.com.veterinaria.app.entities.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.DTO.Address;
import ar.com.veterinaria.app.entities.Request.AddressRequest;
public class AddressRequestImpl implements AddressRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setAddress(Address address,Integer id){
 restTemplate.put("http://4/Person/{id}/Address/setAddress",address,id);
 return ;
}


public Address getAddress(Integer id){
 Address aux = restTemplate.getForObject("http://4/Person/{id}/Address/getAddress",Address.class,id);
return aux;
}


}