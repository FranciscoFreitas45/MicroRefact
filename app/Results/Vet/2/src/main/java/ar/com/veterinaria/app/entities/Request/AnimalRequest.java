package ar.com.veterinaria.app.entities.Request;
import ar.com.veterinaria.app.entities.DTO.Animal;
public interface AnimalRequest {

   public Animal getAnimal(Integer id);
   public void setAnimal(Animal animal,Integer id);
}