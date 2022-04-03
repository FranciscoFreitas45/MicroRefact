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
import com.cocay.sicecd.Request.CursoRequest;
import com.cocay.sicecd.Request.Impl.CursoRequestImpl;
import com.cocay.sicecd.DTO.Curso;
import com.cocay.sicecd.Request.ProfesorRequest;
import com.cocay.sicecd.Request.Impl.ProfesorRequestImpl;
import com.cocay.sicecd.DTO.Profesor;
import com.cocay.sicecd.Request.CertificadoRequest;
import com.cocay.sicecd.Request.Impl.CertificadoRequestImpl;
import com.cocay.sicecd.DTO.Certificado;
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


public Curso getFk_id_curso(){
    return fk_id_curso;
}


public String getTempCurso(){
    return tempCurso;
}


public List<Certificado> getCertificados(){
    return certificados;
}


public Profesor getFk_id_profesor(){
    return fk_id_profesor;
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


public void setInscripciones(List<Inscripcion> inscripciones){
    this.inscripciones = inscripciones;
}


public void setFecha_inicio(Date fecha_inicio){
    this.fecha_inicio = fecha_inicio;
}


public Date getFecha_fin(){
    return fecha_fin;
}


}