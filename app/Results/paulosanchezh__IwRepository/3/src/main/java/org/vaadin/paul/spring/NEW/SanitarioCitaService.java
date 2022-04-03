package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.SanitarioRepository;
import org.vaadin.paul.spring.entities.Sanitario;
@Service
public class SanitarioCitaService {

@Autowired
 private SanitarioRepository sanitariorepository;


public Sanitario getSanitario(int id){
return sanitariorepository.getSanitario(id);
}


public void setSanitario(int id,Sanitario sanitario){
sanitariorepository.setSanitario(id,sanitario);
}


}