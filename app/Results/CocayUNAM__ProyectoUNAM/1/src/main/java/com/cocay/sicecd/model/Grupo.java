package com.cocay.sicecd.model;
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
@Entity
@Table(name = "Grupo", uniqueConstraints = @UniqueConstraint(columnNames = { "fk_id_curso", "clave" }))
public class Grupo {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_grupo")
 private int pk_id_grupo;

@Column(name = "clave", nullable = false, length = 8, unique = false)
 private String clave;

@Column(name = "fecha_inicio", nullable = true)
 private Date fecha_inicio;

@Column(name = "fecha_fin", nullable = true)
 private Date fecha_fin;

@Column(name = "tempCurso", nullable = true)
 private  String tempCurso;

@Column(name = "tempProfesor", nullable = true)
 private  String tempProfesor;

@Column(name = "stTabla", nullable = true)
 private Integer stTabla;

@Transient
 private Curso fk_id_curso;

@Transient
 private Profesor fk_id_profesor;

@OneToMany(mappedBy = "fk_id_grupo", targetEntity = Inscripcion.class)
@LazyCollection(LazyCollectionOption.TRUE)
 private  List<Inscripcion> inscripciones;

@Transient
 private  List<Certificado> certificados;

@Column(name = "pk_id_curso")
 private int pk_id_curso;

@Transient
 private CursoRequest cursorequest = new CursoRequestImpl();;

@Column(name = "pk_id_profesor")
 private int pk_id_profesor;

@Transient
 private ProfesorRequest profesorrequest = new ProfesorRequestImpl();;

@Transient
 private CertificadoRequest certificadorequest = new CertificadoRequestImpl();;


public void setCertificados(List<Certificado> certificados){
 certificadorequest.setCertificados(certificados,this.pk_id_grupo);
}



public Curso getFk_id_curso(){
  this.fk_id_curso = cursorequest.getFk_id_curso(this.pk_id_curso);
return this.fk_id_curso;
}


public String getTempCurso(){
    return tempCurso;
}


public void setTempCurso(String tempCurso){
    this.tempCurso = tempCurso;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequest.getCertificados(this.pk_id_grupo);
return this.certificados;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequest.getFk_id_profesor(this.pk_id_profesor);
return this.fk_id_profesor;
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
 cursorequest.setFk_id_curso(curso,this.pk_id_curso);
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
 profesorrequest.setFk_id_profesor(fk_id_profesor,this.pk_id_profesor);
}



public Date getFecha_fin(){
    return fecha_fin;
}


}