package ar.com.veterinaria.app.entities.Request;
import ar.com.veterinaria.app.entities.DTO.AnimalBreed;
public interface AnimalBreedRequest {

   public void setAnimalBreed(AnimalBreed animalBreed,Integer id);
   public AnimalBreed getAnimalBreed(Integer id);
}