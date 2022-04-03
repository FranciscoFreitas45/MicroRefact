package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.AnimalBreed;
public interface AnimalBreedRepository extends JpaRepository<AnimalBreed, Integer>{


public AnimalBreed findAnimalBreedById(Integer id)
;

@SuppressWarnings("unchecked")
public AnimalBreed save(AnimalBreed animalBreed)
;

public List<AnimalBreed> findAll()
;

public void delete(AnimalBreed snimalBreed)
;

public void setAnimalBreed(Integer id,AnimalBreed animalBreed);

public AnimalBreed getAnimalBreed(Integer id);

}