package org.vaadin.paul.spring.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vaadin.paul.spring.entities.Localidad;
import org.vaadin.paul.spring.entities.Provincia;
public interface LocalidadRepository extends JpaRepository<Localidad, Integer>{


public List<Localidad> findByprovincia(Provincia provincia)
;

}