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
import co.edu.uniquindio.gri.Request.ProduccionRequest;
import co.edu.uniquindio.gri.Request.Impl.ProduccionRequestImpl;
import co.edu.uniquindio.gri.DTO.Produccion;
@Entity(name = "TIPOS")
@Table(name = "TIPOS", schema = "gri")
public class Tipo implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE", length = 100)
 private  String nombre;

@Transient
 private  List<Produccion> produccion;

@OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionB> produccionBibliografica;

@OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionGrupo> produccionG;

@OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionBGrupo> produccionBibliograficaG;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "TIPOPRODUCCION_ID")
 private  TipoProduccion tipoProduccion;

@Transient
 private ProduccionRequest produccionrequest = new ProduccionRequestImpl();;

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


public void setProduccionBibliograficaG(List<ProduccionBGrupo> produccionBibliograficaG){
    this.produccionBibliograficaG = produccionBibliograficaG;
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


public void setTipoProduccion(TipoProduccion tipoProduccion){
    this.tipoProduccion = tipoProduccion;
}


public List<Produccion> getProduccion(){
  this.produccion = produccionrequest.getProduccion(this.id);
return this.produccion;
}


public void setProduccion(List<Produccion> produccion){
 produccionrequest.setProduccion(produccion,this.id);
}



public void setProduccionG(List<ProduccionGrupo> produccionG){
    this.produccionG = produccionG;
}


public void setId(long id){
    this.id = id;
}


public List<ProduccionB> getProduccionBibliografica(){
    return produccionBibliografica;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setProduccionBibliografica(List<ProduccionB> produccionBibliografica){
    this.produccionBibliografica = produccionBibliografica;
}


}