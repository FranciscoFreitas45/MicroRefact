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

 private CursoRequestImpl cursorequestimpl;

 private int pk_id_profesor;

 private ProfesorRequestImpl profesorrequestimpl;

 private InscripcionRequestImpl inscripcionrequestimpl;

 private CertificadoRequestImpl certificadorequestimpl;


public Curso getFk_id_curso(){
  this.fk_id_curso = cursorequestimpl.getFk_id_curso(this.int);
return this.fk_id_curso;
}


public String getTempCurso(){
    return tempCurso;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequestimpl.getCertificados(this.pk_id_grupo);
return this.certificados;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequestimpl.getFk_id_profesor(this.int);
return this.fk_id_profesor;
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


public Date getFecha_fin(){
    return fecha_fin;
}


}