package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vaadin.paul.spring.entities.Trabajador;
import org.vaadin.paul.spring.entities.User;
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{


@Query(value = "SELECT * FROM trabajador WHERE id_centro IS NULL", nativeQuery = true)
public List<Trabajador> findByTrabajadoresNulos()
;

public Trabajador findByUser(User usuario)
;

public Trabajador findByuser(User usuario)
;

public void setTrabajador(int id,Trabajador trabajador);

public Trabajador getTrabajador(int id);

public List<Trabajador> getTrabajadores(int id);

public void setTrabajadores(int id,List<Trabajador> trabajadores);

}