package ar.com.veterinaria.app.entities.service.contract;
 import ar.com.veterinaria.app.entities.Animal;
public interface AnimalContractService extends BaseContractService<Animal>{


public Animal findAnimalByName(String animal)
;

}