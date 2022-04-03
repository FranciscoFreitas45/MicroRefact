package com.cocay.sicecd.DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
public class Grupo {

 private int pk_id_grupo;

 private String clave;

 private Date fecha_inicio;

 private Date fecha_fin;

 private  String tempCurso;

 private  String tempProfesor;

 private Integer stTabla;

 private Curso fk_id_curso;

 private Profesor fk_id_profesor;

 private  List<Inscripcion> inscripciones;

 private  List<Certificado> certificados;

 private int pk_id_curso;


public void setCertificados(List<Certificado> certificados){
    this.certificados = certificados;
}


public Curso getFk_id_curso(){
    return fk_id_curso;
}


public String getTempCurso(){
    return tempCurso;
}


public void setTempCurso(String tempCurso){
    this.tempCurso = tempCurso;
}


public List<Certificado> getCertificados(){
    return certificados;
}


public Profesor getFk_id_profesor(){
    return fk_id_profesor;
}


public void setTempProfesor(String tempProfesor){
    this.tempProfesor = tempProfesor;
}


public void setFecha_fin(Date fecha_fin){
    this.fecha_fin = fecha_fin;
}


public String getTempProfesor(){
    return tempProfesor;
}


public Integer getStTabla(){
    return stTabla;
}


public Date getFecha_inicio(){
    return fecha_inicio;
}


public void setStTabla(Integer stTabla){
    this.stTabla = stTabla;
}


public void setPk_id_grupo(int pk_id_grupo){
    this.pk_id_grupo = pk_id_grupo;
}


public List<Inscripcion> getInscripciones(){
    return inscripciones;
}


public int getPk_id_grupo(){
    return pk_id_grupo;
}


public String getClave(){
    return clave;
}


public void setFk_id_curso(Curso curso){
    this.fk_id_curso = curso;
}


public void setInscripciones(List<Inscripcion> inscripciones){
    this.inscripciones = inscripciones;
}


public void setClave(String clave){
    this.clave = clave;
}


public void setFecha_inicio(Date fecha_inicio){
    this.fecha_inicio = fecha_inicio;
}


public void setFk_id_profesor(Profesor fk_id_profesor){
    this.fk_id_profesor = fk_id_profesor;
}


public Date getFecha_fin(){
    return fecha_fin;
}


}