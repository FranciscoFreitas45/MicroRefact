package com.cocay.sicecd.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.CursoRep;
import com.cocay.sicecd.model.Curso;
@Service
public class CursoCertificadoService {

@Autowired
 private CursoRep cursorep;


public Curso getFk_id_curso(int pk_id_curso){
return cursorep.getFk_id_curso(pk_id_curso);
}


public void setFk_id_curso(int pk_id_curso,Curso fk_id_curso){
cursorep.setFk_id_curso(pk_id_curso,fk_id_curso);
}


}