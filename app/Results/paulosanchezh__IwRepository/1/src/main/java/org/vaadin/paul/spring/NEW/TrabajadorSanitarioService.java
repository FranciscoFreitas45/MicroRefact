package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.TrabajadorRepository;
import org.vaadin.paul.spring.entities.Trabajador;
@Service
public class TrabajadorSanitarioService {

@Autowired
 private TrabajadorRepository trabajadorrepository;


public void setTrabajador(int id,Trabajador trabajador){
trabajadorrepository.setTrabajador(id,trabajador);
}


public Trabajador getTrabajador(int id){
return trabajadorrepository.getTrabajador(id);
}


}