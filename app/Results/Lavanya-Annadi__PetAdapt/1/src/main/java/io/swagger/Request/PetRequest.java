package io.swagger.Request;
import io.swagger.DTO.Pet;
public interface PetRequest {

   public Pet getPet(Long id);
   public void setPet(Pet pet,Long id);
}