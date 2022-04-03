package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.Breed;
public interface BreedRepository extends JpaRepository<Breed, Integer>{


@SuppressWarnings("unchecked")
public Breed save(Breed breed)
;

public Breed findBreedById(Integer id)
;

public List<Breed> findAll()
;

public void delete(Breed breed)
;

public void setBreed(Integer id,Breed breed);

public Breed getBreed(Integer id);

}