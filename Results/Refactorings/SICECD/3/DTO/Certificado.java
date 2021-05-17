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
public class Certificado {

 private  int pk_id_certificado;

 private  String ruta;

 private  long tiempo_creado;

 private  Profesor fk_id_profesor;

 private  Curso fk_id_curso;

 private  Grupo fk_id_grupo;

 private int pk_id_profesor;

 private ProfesorRequestImpl profesorrequestimpl;

 private int pk_id_curso;

 private CursoRequestImpl cursorequestimpl;

 private int pk_id_grupo;

 private GrupoRequestImpl gruporequestimpl;


public int getPk_id_certificado(){
    return pk_id_certificado;
}


public long getTiempo_creado(){
    return this.tiempo_creado;
}


public Curso getFk_id_curso(){
  this.fk_id_curso = cursorequestimpl.getFk_id_curso(this.int);
return this.fk_id_curso;
}


public String getRuta(){
    return ruta;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequestimpl.getFk_id_profesor(this.int);
return this.fk_id_profesor;
}


public Grupo getFk_id_grupo(){
  this.fk_id_grupo = gruporequestimpl.getFk_id_grupo(this.int);
return this.fk_id_grupo;
}


}