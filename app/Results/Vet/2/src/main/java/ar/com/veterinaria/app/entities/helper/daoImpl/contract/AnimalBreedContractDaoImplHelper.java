package ar.com.veterinaria.app.entities.helper.daoImpl.contract;
 import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.Breed;
public interface AnimalBreedContractDaoImplHelper extends BaseContractDaoImplHelper<AnimalBreed>{


public AnimalBreed findAnimalBreedByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed name)
;

public Animal findAnimalByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed name)
;

public Breed findBreedByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed name)
;

}