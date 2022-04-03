package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.CitaRepository;
import org.vaadin.paul.spring.entities.Cita;
@Service
public class CitaHistorialClinicoService {

@Autowired
 private CitaRepository citarepository;


public List<Cita> getCitas(int id){
return citarepository.getCitas(id);
}


}