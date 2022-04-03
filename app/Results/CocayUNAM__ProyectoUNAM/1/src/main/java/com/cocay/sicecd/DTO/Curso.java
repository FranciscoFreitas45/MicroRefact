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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
public class Curso {

 private int pk_id_curso;

 private String clave;

 private String nombre;

 private Integer horas;

 public  Integer temp;

 public  String tempGrupo;

 public  String tempTipoCurso;

 private Integer stTabla;

 private Grupo fk_id_grupo;

 private Tipo_curso fk_id_tipo_curso;

 private  List<Grupo> grupos;

 private  List<Certificado> certificados;

public Curso() {
}public Curso(String clave, String nombre, int horas) {
    super();
    this.clave = clave;
    this.nombre = nombre;
    this.horas = horas;
}
public Tipo_curso getFk_id_tipo_curso(){
    return fk_id_tipo_curso;
}


public List<Certificado> getCertificados(){
    return certificados;
}


public String getNombre(){
    return nombre;
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


public Integer getTemp(){
    return temp;
}


public String getTempTipoCurso(){
    return tempTipoCurso;
}


public String getClave(){
    return clave;
}


public String getTempGrupo(){
    return tempGrupo;
}


public List<Grupo> getGrupos(){
    return grupos;
}


public Grupo getFk_id_grupo(){
    return fk_id_grupo;
}


}