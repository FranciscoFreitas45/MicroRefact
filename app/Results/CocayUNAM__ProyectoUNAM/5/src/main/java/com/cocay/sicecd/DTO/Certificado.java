package com.cocay.sicecd.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.cocay.sicecd.Request.ProfesorRequest;
import com.cocay.sicecd.Request.Impl.ProfesorRequestImpl;
import com.cocay.sicecd.DTO.Profesor;
import com.cocay.sicecd.Request.CursoRequest;
import com.cocay.sicecd.Request.Impl.CursoRequestImpl;
import com.cocay.sicecd.DTO.Curso;
import com.cocay.sicecd.Request.GrupoRequest;
import com.cocay.sicecd.Request.Impl.GrupoRequestImpl;
import com.cocay.sicecd.DTO.Grupo;
public class Certificado {

 private  int pk_id_certificado;

 private  String ruta;

 private  long tiempo_creado;

 private  Profesor fk_id_profesor;

 private  Curso fk_id_curso;

 private  Grupo fk_id_grupo;

 private int pk_id_profesor;

 private int pk_id_curso;

 private int pk_id_grupo;


public int getPk_id_certificado(){
    return pk_id_certificado;
}


public long getTiempo_creado(){
    return this.tiempo_creado;
}


public Curso getFk_id_curso(){
  this.fk_id_curso = cursorequest.getFk_id_curso(this.pk_id_curso);
return this.fk_id_curso;
}


public String getRuta(){
    return ruta;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequest.getFk_id_profesor(this.pk_id_profesor);
return this.fk_id_profesor;
}


public Grupo getFk_id_grupo(){
  this.fk_id_grupo = gruporequest.getFk_id_grupo(this.pk_id_grupo);
return this.fk_id_grupo;
}


}