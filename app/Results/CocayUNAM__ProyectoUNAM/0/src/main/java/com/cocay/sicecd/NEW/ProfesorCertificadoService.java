package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.ProfesorRep;
import com.cocay.sicecd.model.Profesor;
@Service
public class ProfesorCertificadoService {

@Autowired
 private ProfesorRep profesorrep;


public Profesor getFk_id_profesor(int pk_id_profesor){
return profesorrep.getFk_id_profesor(pk_id_profesor);
}


public void setFk_id_profesor(int pk_id_profesor,Profesor fk_id_profesor){
profesorrep.setFk_id_profesor(pk_id_profesor,fk_id_profesor);
}


}