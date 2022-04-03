package ar.com.veterinaria.app.entities.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.DTO.Breed;
import ar.com.veterinaria.app.entities.Request.BreedRequest;
public class BreedRequestImpl implements BreedRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setBreed(Breed breed,Integer id){
 restTemplate.put("http://7/AnimalBreed/{id}/Breed/setBreed",breed,id);
 return ;
}


public Breed getBreed(Integer id){
 Breed aux = restTemplate.getForObject("http://7/AnimalBreed/{id}/Breed/getBreed",Breed.class,id);
return aux;
}


}