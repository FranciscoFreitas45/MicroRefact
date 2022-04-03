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
public class Tipo implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  List<Produccion> produccion;

 private  List<ProduccionB> produccionBibliografica;

 private  List<ProduccionGrupo> produccionG;

 private  List<ProduccionBGrupo> produccionBibliograficaG;

 private  TipoProduccion tipoProduccion;

/**
 * Instantiates a new tipo.
 */
public Tipo() {
}/**
 * Instantiates a new tipo.
 *
 * @param id the id
 * @param nombre the nombre
 */
public Tipo(long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
}
public List<ProduccionGrupo> getProduccionG(){
    return produccionG;
}


public String getNombre(){
    return nombre;
}


public TipoProduccion getTipoProduccion(){
    return tipoProduccion;
}


public long getId(){
    return id;
}


public List<ProduccionBGrupo> getProduccionBibliograficaG(){
    return produccionBibliograficaG;
}


public List<Produccion> getProduccion(){
    return produccion;
}


public List<ProduccionB> getProduccionBibliografica(){
    return produccionBibliografica;
}


}