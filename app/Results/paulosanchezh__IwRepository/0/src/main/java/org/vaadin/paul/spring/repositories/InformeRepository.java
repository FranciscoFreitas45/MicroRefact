package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.Informe;
public interface InformeRepository extends JpaRepository<Informe, Integer>{


public Informe findByid(Integer id)
;

public Informe findByCita(Cita cita)
;

}