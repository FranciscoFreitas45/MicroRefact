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
@Entity(name = "TIPOPRODUCCION")
@Table(name = "TIPOPRODUCCION", schema = "gri")
public class TipoProduccion implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE", length = 100)
 private  String nombre;

@OneToMany(mappedBy = "tipoProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<Tipo> tipos;

/**
 * Instantiates a new tipo produccion.
 */
public TipoProduccion() {
}/**
 * Instantiates a new tipo produccion.
 *
 * @param id the id
 * @param nombre the nombre
 */
public TipoProduccion(long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
}
public void setTipos(List<Tipo> tipos){
    this.tipos = tipos;
}


public String getNombre(){
    return nombre;
}


public List<Tipo> getTipos(){
    return tipos;
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


}