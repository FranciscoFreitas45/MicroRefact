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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "Curso")
public class Curso {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_curso")
 private int pk_id_curso;

@Column(name = "clave", nullable = false, length = 8, unique = true)
 private String clave;

@Column(name = "nombre", nullable = false, length = 150)
 private String nombre;

@Column(name = "horas", nullable = true)
 private Integer horas;

@Column(name = "temp", nullable = true)
 public  Integer temp;

@Column(name = "tempGrupo", nullable = true)
 public  String tempGrupo;

@Column(name = "tempTipoCurso", nullable = true)
 public  String tempTipoCurso;

@Column(name = "stTabla", nullable = true)
 private Integer stTabla;

@Transient
 private Grupo fk_id_grupo;

@ManyToOne(targetEntity = Tipo_curso.class)
@LazyCollection(LazyCollectionOption.TRUE)
@JoinColumn(name = "fk_id_tipo_curso", referencedColumnName = "pk_id_tipo_curso")
 private Tipo_curso fk_id_tipo_curso;

@Transient
 private  List<Grupo> grupos;

@Transient
 private  List<Certificado> certificados;

@Column(name = pk_id_grupo)
 private int pk_id_grupo;

@Transient
 private GrupoRequestImpl gruporequestimpl;

@Transient
 private GrupoRequestImpl gruporequestimpl;

@Transient
 private CertificadoRequestImpl certificadorequestimpl;


public Tipo_curso getFk_id_tipo_curso(){
    return fk_id_tipo_curso;
}


public void setGrupos(List<Grupo> grupos){
 gruporequestimpl.setGrupos(grupos,this.pk_id_curso);



public void setCertificados(List<Certificado> certificados){
 certificadorequestimpl.setCertificados(certificados,this.pk_id_curso);



public void setHoras(Integer horas){
    this.horas = horas;
}


public List<Certificado> getCertificados(){
  this.certificados = certificadorequestimpl.getCertificados(this.pk_id_curso);
return this.certificados;
}


public String getNombre(){
    return nombre;
}


public void setTemp(Integer temp){
    this.temp = temp;
}


public void setFk_id_grupo(Grupo fk_id_grupo){
 gruporequestimpl.setFk_id_grupo(fk_id_grupo,this.pk_id_curso);



public void setPk_id_curso(int pk_id_curso){
    this.pk_id_curso = pk_id_curso;
}


public int getPk_id_curso(){
    return pk_id_curso;
}


public Integer getStTabla(){
    return stTabla;
}


public int getHoras(){
    return horas;
}


public void setStTabla(Integer stTabla){
    this.stTabla = stTabla;
}


public void setTempGrupo(String tempGrupo){
    this.tempGrupo = tempGrupo;
}


public Integer getTemp(){
    return temp;
}


public String getTempTipoCurso(){
    return tempTipoCurso;
}


public void setFk_id_tipo_curso(Tipo_curso fk_id_tipo_curso){
    this.fk_id_tipo_curso = fk_id_tipo_curso;
}


public String getClave(){
    return clave;
}


public void setClave(String clave){
    this.clave = clave;
}


public String getTempGrupo(){
    return tempGrupo;
}


public void setTempTipoCurso(String tempTipoCurso){
    this.tempTipoCurso = tempTipoCurso;
}


public List<Grupo> getGrupos(){
  this.grupos = gruporequestimpl.getGrupos(this.pk_id_curso);
return this.grupos;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public Grupo getFk_id_grupo(){
  this.grupos = gruporequestimpl.getFk_id_grupo(this.pk_id_curso);
return this.grupos;
}


}