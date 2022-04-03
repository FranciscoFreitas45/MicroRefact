package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.paul.spring.entities.Comunidad;
public interface ComunidadRepository extends JpaRepository<Comunidad, Integer>{


public Comunidad findByid(int id)
;

}