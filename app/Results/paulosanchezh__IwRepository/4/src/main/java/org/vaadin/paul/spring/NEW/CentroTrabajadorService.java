package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.CentroRepository;
import org.vaadin.paul.spring.entities.Centro;
@Service
public class CentroTrabajadorService {

@Autowired
 private CentroRepository centrorepository;


public Centro getCentro(int id){
return centrorepository.getCentro(id);
}


public void setCentro(int id,Centro centro){
centrorepository.setCentro(id,centro);
}


}