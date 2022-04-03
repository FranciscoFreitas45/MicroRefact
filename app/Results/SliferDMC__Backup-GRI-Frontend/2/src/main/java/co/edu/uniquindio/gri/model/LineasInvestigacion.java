package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name = "LINEASINVESTIGACION")
@Table(name = "LINEASINVESTIGACION", schema = "gri")
public class LineasInvestigacion implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE", length = 400)
 private  String nombre;

@ManyToMany(mappedBy = "lineasInvestigacion", cascade = CascadeType.ALL)
@JsonIgnore
 private  List<Investigador> investigadores;

@ManyToMany(mappedBy = "lineasInvestigacion", cascade = CascadeType.ALL)
@JsonIgnore
 private  List<Grupo> grupos;

/**
 * Instantiates a new lineas investigacion.
 */
public LineasInvestigacion() {
}/**
 * Instantiates a new lineas investigacion.
 *
 * @param id the id
 * @param nombre the nombre
 */
public LineasInvestigacion(long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
}
public void setGrupos(List<Grupo> grupos){
    this.grupos = grupos;
}


public void setInvestigadores(List<Investigador> investigadores){
    this.investigadores = investigadores;
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


public List<Investigador> getInvestigadores(){
    return investigadores;
}


}