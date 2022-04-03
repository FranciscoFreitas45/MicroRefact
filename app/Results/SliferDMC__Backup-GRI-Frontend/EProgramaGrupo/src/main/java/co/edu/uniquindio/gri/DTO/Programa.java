package co.edu.uniquindio.gri.DTO;
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
public class Programa implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  Facultad facultad;

 private  List<Grupo> grupos;

 private long id;

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
    this.facultad = facultad;
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
    return facultad;
}


}