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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.GrupoRequest;
import co.edu.uniquindio.gri.Request.Impl.GrupoRequestImpl;
import co.edu.uniquindio.gri.DTO.Grupo;
@Entity(name = "CENTROS")
@Table(name = "CENTROS", schema = "gri")
public class Centro implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE")
 private  String nombre;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "FACULTADES_ID")
 private  Facultad facultad;

@Transient
 private  List<Grupo> grupo;

@Transient
 private GrupoRequest gruporequest = new GrupoRequestImpl();;

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
public void setFacultad(Facultad facultad){
    this.facultad = facultad;
}


public String getNombre(){
    return nombre;
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


public void setGrupo(List<Grupo> grupo){
 gruporequest.setGrupo(grupo,this.id);
}



public List<Grupo> getGrupo(){
  this.grupo = gruporequest.getGrupo(this.id);
return this.grupo;
}


public Facultad getFacultad(){
    return facultad;
}


}