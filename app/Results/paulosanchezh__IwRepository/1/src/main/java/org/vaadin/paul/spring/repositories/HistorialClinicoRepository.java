package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.HistorialClinico;
import org.vaadin.paul.spring.entities.Informe;
import org.vaadin.paul.spring.entities.User;
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Integer>{


public HistorialClinico findByPaciente(User paciente)
;

}