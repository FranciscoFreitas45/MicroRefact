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
@Entity
@Table(name = "Certificado")
public class Certificado {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_certificado")
 private  int pk_id_certificado;

@Column(name = "ruta", nullable = false, length = 250, unique = true)
 private  String ruta;

@Column(name = "tiempo_creado", nullable = false)
 private  long tiempo_creado;

@Transient
 private  Profesor fk_id_profesor;

@Transient
 private  Curso fk_id_curso;

@Transient
 private  Grupo fk_id_grupo;

@Column(name = pk_id_profesor)
 private int pk_id_profesor;

@Transient
 private ProfesorRequestImpl profesorrequestimpl;

@Column(name = pk_id_curso)
 private int pk_id_curso;

@Transient
 private CursoRequestImpl cursorequestimpl;

@Column(name = pk_id_grupo)
 private int pk_id_grupo;

@Transient
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


public void setFk_id_curso(Curso fk_id_curso){
 cursorequestimpl.setFk_id_curso(fk_id_curso,this.int);



public void setFk_id_profesor(Profesor fk_id_profesor){
 profesorrequestimpl.setFk_id_profesor(fk_id_profesor,this.int);



public void setFk_id_grupo(Grupo fk_id_grupo){
 gruporequestimpl.setFk_id_grupo(fk_id_grupo,this.int);



public void setRuta(String ruta){
    this.ruta = ruta;
}


public Grupo getFk_id_grupo(){
  this.fk_id_grupo = gruporequestimpl.getFk_id_grupo(this.int);
return this.fk_id_grupo;
}


public void setTiempo_creado(long tiempo_creado){
    this.tiempo_creado = tiempo_creado;
}


public void setPk_id_certificado(int pk_id_certificado){
    this.pk_id_certificado = pk_id_certificado;
}


}