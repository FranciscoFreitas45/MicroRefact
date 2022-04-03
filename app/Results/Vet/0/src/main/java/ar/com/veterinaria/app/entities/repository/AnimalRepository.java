package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.Animal;
public interface AnimalRepository extends JpaRepository<Animal, Integer>{


@SuppressWarnings("unchecked")
public Animal save(Animal animal)
;

public Animal findAnimalById(Integer id)
;

public List<Animal> findAll()
;

public void delete(Animal animal)
;

public Animal getAnimal(Integer id);

public void setAnimal(Integer id,Animal animal);

}