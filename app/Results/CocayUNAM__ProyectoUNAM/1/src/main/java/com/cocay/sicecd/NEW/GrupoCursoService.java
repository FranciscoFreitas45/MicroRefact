package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.GrupoRep;
import com.cocay.sicecd.model.Grupo;
@Service
public class GrupoCursoService {

@Autowired
 private GrupoRep gruporep;


public void setGrupos(int pk_id_curso,List<Grupo> grupos){
gruporep.setGrupos(pk_id_curso,grupos);
}


public void setFk_id_grupo(int pk_id_curso,Grupo fk_id_grupo){
gruporep.setFk_id_grupo(pk_id_curso,fk_id_grupo);
}


public List<Grupo> getGrupos(int pk_id_curso){
return gruporep.getGrupos(pk_id_curso);
}


public Grupo getFk_id_grupo(int pk_id_curso){
return gruporep.getFk_id_grupo(pk_id_curso);
}


}