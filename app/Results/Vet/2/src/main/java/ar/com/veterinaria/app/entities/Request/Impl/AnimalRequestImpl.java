package ar.com.veterinaria.app.entities.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.DTO.Animal;
import ar.com.veterinaria.app.entities.Request.AnimalRequest;
public class AnimalRequestImpl implements AnimalRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Animal getAnimal(Integer id){
 Animal aux = restTemplate.getForObject("http://0/AnimalBreed/{id}/Animal/getAnimal",Animal.class,id);
return aux;
}


public void setAnimal(Animal animal,Integer id){
 restTemplate.put("http://0/AnimalBreed/{id}/Animal/setAnimal",animal,id);
 return ;
}


}