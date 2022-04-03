package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.paul.spring.entities.Rol;
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{


public List<Rol> findAll()
;

}