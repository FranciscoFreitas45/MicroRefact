package org.vaadin.paul.spring.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vaadin.paul.spring.entities.Sanitario;
import org.vaadin.paul.spring.entities.Trabajador;
import org.springframework.beans.factory.BeanCreationException;
@Repository
public interface SanitarioRepository extends JpaRepository<Sanitario, Integer>{


@Query(value = "SELECT count(*) FROM cita WHERE id_sanitario = ?1", nativeQuery = true)
public int countBySanitario(int id)
;

public Sanitario findByTrabajador(Trabajador trabajador)
;

public Sanitario findById(int id)
;

@Query(value = "SELECT * FROM sanitario WHERE id_trabajador = ?1", nativeQuery = true)
public Sanitario findByTrabajadorId(int id)
;

public Sanitario getSanitario(int id);

public void setSanitario(int id,Sanitario sanitario);

}