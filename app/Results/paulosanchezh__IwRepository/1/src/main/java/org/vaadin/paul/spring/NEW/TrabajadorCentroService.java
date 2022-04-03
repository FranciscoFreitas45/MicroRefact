package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.TrabajadorRepository;
import org.vaadin.paul.spring.entities.Trabajador;
@Service
public class TrabajadorCentroService {

@Autowired
 private TrabajadorRepository trabajadorrepository;


public List<Trabajador> getTrabajadores(int id){
return trabajadorrepository.getTrabajadores(id);
}


public void setTrabajadores(int id,List<Trabajador> trabajadores){
trabajadorrepository.setTrabajadores(id,trabajadores);
}


}