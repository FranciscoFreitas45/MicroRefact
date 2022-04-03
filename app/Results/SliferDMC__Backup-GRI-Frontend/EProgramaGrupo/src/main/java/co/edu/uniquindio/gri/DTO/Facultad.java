package co.edu.uniquindio.gri.DTO;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.ProgramaRequest;
import co.edu.uniquindio.gri.Request.Impl.ProgramaRequestImpl;
import co.edu.uniquindio.gri.DTO.Programa;
public class Facultad implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  List<Centro> centros;

 private  List<Programa> programas;

/**
 * Instantiates a new facultad.
 */
public Facultad() {
}/**
 * Instantiates a new facultad.
 *
 * @param id the id
 * @param nombre the nombre
 */
public Facultad(long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
}
public String getNombre(){
    return nombre;
}


public long getId(){
    return id;
}


public List<Centro> getCentros(){
    return centros;
}


@JsonIgnore
public List<Programa> getPrograma(){
  this.programas = programarequest.getPrograma(this.id);
return this.programas;
}


}