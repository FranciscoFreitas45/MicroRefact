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
public class Inscripcion {

 private int pk_id_inscripcion;

 private String calif;

 private Boolean aprobado;

 private String tempGrupo;

 private String tempCurso;

 private String tempProfesor;

 private Integer stTabla;

 private Grupo fk_id_grupo;

 private Profesor fk_id_profesor;

 private int pk_id_profesor;

 private ProfesorRequest profesorrequest = new ProfesorRequestImpl();;

public Inscripcion() {
}
public String getTempCurso(){
    return tempCurso;
}


public void setTempCurso(String tempCurso){
    this.tempCurso = tempCurso;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequest.getFk_id_profesor(this.pk_id_profesor);
return this.fk_id_profesor;
}


public int getPk_id_inscripcion(){
    return pk_id_inscripcion;
}


public void setCalif(String calif){
    this.calif = calif;
}


public void setTempProfesor(String tempProfesor){
    this.tempProfesor = tempProfesor;
}


public void setFk_id_grupo(Grupo fk_id_grupo){
    this.fk_id_grupo = fk_id_grupo;
}


public void setAprobado(Boolean aprobado){
    this.aprobado = aprobado;
}


public String getTempProfesor(){
    return tempProfesor;
}


public Integer getStTabla(){
    return stTabla;
}


public void setPk_id_inscripcion(int pk_id_inscripcion){
    this.pk_id_inscripcion = pk_id_inscripcion;
}


public String getCalif(){
    return calif;
}


public void setStTabla(Integer stTabla){
    this.stTabla = stTabla;
}


public void setTempGrupo(String tempGrupo){
    this.tempGrupo = tempGrupo;
}


public String getTempGrupo(){
    return tempGrupo;
}


public void setFk_id_profesor(Profesor fk_id_profesor){
 profesorrequest.setFk_id_profesor(fk_id_profesor,this.pk_id_profesor);
}



public Boolean isAprobado(){
    return aprobado;
}


public Grupo getFk_id_grupo(){
    return fk_id_grupo;
}


}