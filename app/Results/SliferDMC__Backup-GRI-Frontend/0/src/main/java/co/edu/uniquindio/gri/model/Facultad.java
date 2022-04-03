package co.edu.uniquindio.gri.model;
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
@Entity(name = "FACULTADES")
@Table(name = "FACULTADES", schema = "gri")
public class Facultad implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE")
 private  String nombre;

@OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<Centro> centros;

@Transient
 private  List<Programa> programas;

@Transient
 private ProgramaRequest programarequest = new ProgramaRequestImpl();;

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


public void setPrograma(List<Programa> programa){
 programarequest.setPrograma(programa,this.id);
}



public void setId(long id){
    this.id = id;
}


public void setCentros(List<Centro> centros){
    this.centros = centros;
}


public long getId(){
    return id;
}


public void setNombre(String nombre){
    this.nombre = nombre;
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