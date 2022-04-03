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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.GrupoRequest;
import co.edu.uniquindio.gri.Request.Impl.GrupoRequestImpl;
import co.edu.uniquindio.gri.DTO.Grupo;
public class Centro implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  Facultad facultad;

 private  List<Grupo> grupo;

/**
 * Instantiates a new centro.
 */
public Centro() {
}/**
 * Instantiates a new centro.
 *
 * @param id the id
 * @param nombre the nombre
 * @param facultad the facultad
 */
public Centro(long id, String nombre, Facultad facultad) {
    this.id = id;
    this.nombre = nombre;
    this.facultad = facultad;
}
public String getNombre(){
    return nombre;
}


public long getId(){
    return id;
}


public List<Grupo> getGrupo(){
  this.grupo = gruporequest.getGrupo(this.id);
return this.grupo;
}


public Facultad getFacultad(){
    return facultad;
}


}