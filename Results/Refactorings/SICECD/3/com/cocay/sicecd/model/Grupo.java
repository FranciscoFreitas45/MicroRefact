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

@Transient
 private  List<Inscripcion> inscripciones;

@Transient
 private  List<Certificado> certificados;

@Column(name = pk_id_curso)
 private int pk_id_curso;

@Transient
 private CursoRequestImpl cursorequestimpl;

@Column(name = pk_id_profesor)
 private int pk_id_profesor;

@Transient
 private ProfesorRequestImpl profesorrequestimpl;

@Transient
 private InscripcionRequestImpl inscripcionrequestimpl;

@Transient
 private CertificadoRequestImpl certificadorequestimpl;


public void setCertificados(List<Certificado> certificados){
 certificadorequestimpl.setCertificados(certificados,this.pk_id_grupo);



public Curso getFk_id_curso(){
  this.fk_id_curso = cursorequestimpl.getFk_id_curso(this.int);
return this.fk_id_curso;
}


public String getTempCurso(){
    return tempCurso;
}


public void setTempCurso(String tempCurso){
    this.tempCurso = tempCurso;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequestimpl.getCertificados(this.pk_id_grupo);
return this.certificados;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequestimpl.getFk_id_profesor(this.int);
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
  this.inscripciones = inscripcionrequestimpl.getInscripciones(this.pk_id_grupo);
return this.inscripciones;
}


public int getPk_id_grupo(){
    return pk_id_grupo;
}


public String getClave(){
    return clave;
}


public void setFk_id_curso(Curso curso){
 cursorequestimpl.setFk_id_curso(curso,this.int);



public void setInscripciones(List<Inscripcion> inscripciones){
 inscripcionrequestimpl.setInscripciones(inscripciones,this.pk_id_grupo);



public void setClave(String clave){
    this.clave = clave;
}


public void setFecha_inicio(Date fecha_inicio){
    this.fecha_inicio = fecha_inicio;
}


public void setFk_id_profesor(Profesor fk_id_profesor){
 profesorrequestimpl.setFk_id_profesor(fk_id_profesor,this.int);



public Date getFecha_fin(){
    return fecha_fin;
}


}