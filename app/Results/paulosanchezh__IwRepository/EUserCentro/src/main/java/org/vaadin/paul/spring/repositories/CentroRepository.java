package org.vaadin.paul.spring.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vaadin.paul.spring.entities.Centro;
import org.vaadin.paul.spring.entities.Especialidad;
import org.vaadin.paul.spring.entities.Localidad;
import org.vaadin.paul.spring.entities.Trabajador;
@Repository
public interface CentroRepository extends JpaRepository<Centro, Integer>{


public List<Centro> findByTelefono(String telefono)
;

@Query(value = "SELECT * FROM centro WHERE nombre = ?1 AND NOT id = ?2 ", nativeQuery = true)
public Centro findByName(String nombre,int id)
;

@Query(value = "SELECT * FROM centro, centro_trabajadores WHERE centro.id = centro_trabajadores.centro_id AND centro_trabajadores.trabajadores_id = ?1", nativeQuery = true)
public Centro findByTrabajadores(int id_trabajador)
;

public Centro findBynombre(String nombre)
;

public List<Centro> findByLocalidad(Localidad Localidad)
;

public Centro getCentro(int id);

public void setCentro(int id,Centro centro);

}