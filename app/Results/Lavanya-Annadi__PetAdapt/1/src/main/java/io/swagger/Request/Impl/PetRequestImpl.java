package io.swagger.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.DTO.Pet;
import io.swagger.Request.PetRequest;
public class PetRequestImpl implements PetRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Pet getPet(Long id){
 Pet aux = restTemplate.getForObject("http://3/Order/{id}/Pet/getPet",Pet.class,id);
return aux;
}


public void setPet(Pet pet,Long id){
 restTemplate.put("http://3/Order/{id}/Pet/setPet",pet,id);
 return ;
}


}