package org.vaadin.paul.spring.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.repositories.InformeRepository;
import org.vaadin.paul.spring.entities.Informe;
@Service
public class InformeHistorialClinicoService {

@Autowired
 private InformeRepository informerepository;


}