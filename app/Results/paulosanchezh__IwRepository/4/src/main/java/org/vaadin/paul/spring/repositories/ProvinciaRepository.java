package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.paul.spring.entities.Comunidad;
import org.vaadin.paul.spring.entities.Provincia;
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{


public List<Provincia> findBycomunidad(Comunidad comunidad)
;

public Provincia findById(int id)
;

public List<Provincia> findAll()
;

}