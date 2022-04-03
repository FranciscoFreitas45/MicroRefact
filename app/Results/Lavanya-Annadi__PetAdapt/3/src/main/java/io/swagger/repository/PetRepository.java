package io.swagger.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.Pet;
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long>{


public Pet getPet(Long id);

public void setPet(Long id,Pet pet);

}