package ar.com.veterinaria.app.entities.Request;
import ar.com.veterinaria.app.entities.DTO.Breed;
public interface BreedRequest {

   public void setBreed(Breed breed,Integer id);
   public Breed getBreed(Integer id);
}