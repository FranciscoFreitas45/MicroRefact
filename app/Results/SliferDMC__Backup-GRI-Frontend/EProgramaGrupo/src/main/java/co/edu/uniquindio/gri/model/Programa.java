package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.FacultadRequest;
import co.edu.uniquindio.gri.Request.Impl.FacultadRequestImpl;
import co.edu.uniquindio.gri.DTO.Facultad;
@Entity(name = "PROGRAMAS")
@Table(name = "PROGRAMAS", schema = "gri")
public class Programa implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE")
 private  String nombre;

@Transient
 private  Facultad facultad;

@ManyToMany(mappedBy = "programas", cascade = CascadeType.ALL)
@JsonIgnore
 private  List<Grupo> grupos;

@Column(name = "id")
 private long id;

@Transient
 private FacultadRequest facultadrequest = new FacultadRequestImpl();;

/**
 * Instantiates a new programa.
 */
public Programa() {
}/**
 * Instantiates a new programa.
 *
 * @param id the id
 * @param nombre the nombre
 * @param facultad the facultad
 */
public Programa(long id, String nombre, Facultad facultad) {
    this.id = id;
    this.nombre = nombre;
    this.facultad = facultad;
}
public void setFacultad(Facultad facultad){
 facultadrequest.setFacultad(facultad,this.id);
}



public void setGrupos(List<Grupo> grupos){
    this.grupos = grupos;
}


public String getNombre(){
    return nombre;
}


public List<Grupo> getGrupos(){
    return grupos;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public Facultad getFacultad(){
  this.facultad = facultadrequest.getFacultad(this.id);
return this.facultad;
}


}