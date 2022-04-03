package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.InscripcionRep;
import com.cocay.sicecd.model.Inscripcion;
@Service
public class InscripcionProfesorService {

@Autowired
 private InscripcionRep inscripcionrep;


public List<Inscripcion> getInscripciones(int pk_id_profesor){
return inscripcionrep.getInscripciones(pk_id_profesor);
}


public void setInscripciones(int pk_id_profesor,List<Inscripcion> inscripciones){
inscripcionrep.setInscripciones(pk_id_profesor,inscripciones);
}


}