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
@Table(name = "Inscripcion")
public class Inscripcion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_inscripcion")
 private int pk_id_inscripcion;

@Column(name = "calif", nullable = true, length = 3)
 private String calif;

@Column(name = "aprobado", nullable = true)
 private Boolean aprobado;

@Column(name = "tempGrupo", nullable = true)
 private String tempGrupo;

@Column(name = "tempCurso", nullable = true)
 private String tempCurso;

@Column(name = "tempProfesor", nullable = true)
 private String tempProfesor;

@Column(name = "stTabla", nullable = true)
 private Integer stTabla;

@Transient
 private Grupo fk_id_grupo;

@Transient
 private Profesor fk_id_profesor;

@Column(name = pk_id_grupo)
 private int pk_id_grupo;

@Transient
 private GrupoRequestImpl gruporequestimpl;

@Column(name = pk_id_profesor)
 private int pk_id_profesor;

@Transient
 private ProfesorRequestImpl profesorrequestimpl;


public String getTempCurso(){
    return tempCurso;
}


public void setTempCurso(String tempCurso){
    this.tempCurso = tempCurso;
}


public Profesor getFk_id_profesor(){
  this.fk_id_profesor = profesorrequestimpl.getFk_id_profesor(this.int);
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
 gruporequestimpl.setFk_id_grupo(fk_id_grupo,this.int);



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
 profesorrequestimpl.setFk_id_profesor(fk_id_profesor,this.int);



public Boolean isAprobado(){
    return aprobado;
}


public Grupo getFk_id_grupo(){
  this.fk_id_grupo = gruporequestimpl.getFk_id_grupo(this.int);
return this.fk_id_grupo;
}


}