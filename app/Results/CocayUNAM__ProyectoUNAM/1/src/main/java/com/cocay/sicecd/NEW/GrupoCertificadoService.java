package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.model.Grupo;
@Service
public class GrupoCertificadoService {

@Autowired
 private GrupoRep gruporep;


public void setFk_id_grupo(int pk_id_grupo,Grupo fk_id_grupo){
gruporep.setFk_id_grupo(pk_id_grupo,fk_id_grupo);
}


public Grupo getFk_id_grupo(int pk_id_grupo){
return gruporep.getFk_id_grupo(pk_id_grupo);
}


}