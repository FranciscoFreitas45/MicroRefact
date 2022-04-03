package ar.com.veterinaria.app.entities.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.DTO.AnimalBreed;
import ar.com.veterinaria.app.entities.Request.AnimalBreedRequest;
public class AnimalBreedRequestImpl implements AnimalBreedRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setAnimalBreed(AnimalBreed animalBreed,Integer id){
 restTemplate.put("http://2/Pet/{id}/AnimalBreed/setAnimalBreed",animalBreed,id);
 return ;
}


public AnimalBreed getAnimalBreed(Integer id){
 AnimalBreed aux = restTemplate.getForObject("http://2/Pet/{id}/AnimalBreed/getAnimalBreed",AnimalBreed.class,id);
return aux;
}


}