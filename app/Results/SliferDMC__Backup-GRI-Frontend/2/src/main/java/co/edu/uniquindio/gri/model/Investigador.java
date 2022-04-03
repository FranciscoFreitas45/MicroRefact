package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.ProduccionRequest;
import co.edu.uniquindio.gri.Request.Impl.ProduccionRequestImpl;
import co.edu.uniquindio.gri.DTO.Produccion;
@Entity(name = "INVESTIGADORES")
@Table(name = "INVESTIGADORES", schema = "gri")
public class Investigador implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID")
 private  long id;

@Column(name = "NOMBRE", length = 200)
 private  String nombre;

@Column(name = "CATEGORIA", length = 200)
 private  String categoria;

@Column(name = "NIVELACADEMICO", length = 200)
 private  String nivelAcademico;

@Column(name = "PERTENENCIA", length = 50)
 private  String pertenencia;

@OneToMany(mappedBy = "investigador", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<Idiomas> idiomas;

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "INVEST_LINEAS", joinColumns = { @JoinColumn(name = "INVESTIGADORES_ID") }, inverseJoinColumns = { @JoinColumn(name = "LINEASINVESTIGACION_ID") }, schema = "gri")
 private  List<LineasInvestigacion> lineasInvestigacion;

@Transient
 private  List<Produccion> producciones;

@OneToMany(mappedBy = "investigador", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionB> produccionesBibliograficas;

@OneToMany(mappedBy = "investigadores", cascade = CascadeType.ALL)
 private  List<GruposInves> grupos;

@Transient
 private ProduccionRequest produccionrequest = new ProduccionRequestImpl();;

/**
 * Instantiates a new investigador.
 *
 * @param id the id
 * @param nombre the nombre
 * @param categoria the categoria
 * @param nivelAcademico the nivel academico
 * @param idiomas the idiomas
 * @param lineasInvestigacion the lineas investigacion
 * @param producciones the producciones
 * @param produccionesBibliograficas the producciones bibliograficas
 */
public Investigador(long id, String nombre, String categoria, String nivelAcademico, List<Idiomas> idiomas, List<LineasInvestigacion> lineasInvestigacion, List<Produccion> producciones, List<ProduccionB> produccionesBibliograficas) {
    this.id = id;
    this.nombre = nombre;
    this.categoria = categoria;
    this.nivelAcademico = nivelAcademico;
    this.idiomas = idiomas;
    this.lineasInvestigacion = lineasInvestigacion;
    this.produccionesBibliograficas = produccionesBibliograficas;
}/**
 * Instantiates a new investigador.
 *
 * @param id the id
 * @param nombre the nombre
 * @param categoria the categoria
 * @param nivelAcademico the nivel academico
 * @param pertenencia the pertenencia
 */
public Investigador(long id, String nombre, String categoria, String nivelAcademico, String pertenencia) {
    this.id = id;
    this.nombre = nombre;
    this.categoria = categoria;
    this.nivelAcademico = nivelAcademico;
    this.pertenencia = pertenencia;
}/**
 * Instantiates a new investigador.
 */
public Investigador() {
}
public void setNivelAcademico(String nivelAcademico){
    this.nivelAcademico = nivelAcademico;
}


public void setGrupos(List<GruposInves> grupos){
    this.grupos = grupos;
}


public void setPertenencia(String pertenencia){
    this.pertenencia = pertenencia;
}


public String getNombre(){
    return nombre;
}


public void setProducciones(List<Produccion> producciones){
 produccionrequest.setProducciones(producciones,this.id);
}



public void setLineasInvestigacion(List<LineasInvestigacion> lineasInvestigacion){
    this.lineasInvestigacion = lineasInvestigacion;
}


public void setCategoria(String categoria){
    this.categoria = categoria;
}


public void removeLineasInvestigacion(LineasInvestigacion lineas){
    lineasInvestigacion.remove(lineas);
    lineas.getInvestigadores().remove(this);
}


public long getId(){
    return id;
}


public String getNivelAcademico(){
    return nivelAcademico;
}


public void setIdiomas(List<Idiomas> idiomas){
    this.idiomas = idiomas;
}


public String getPertenencia(){
    return pertenencia;
}


public List<Idiomas> getIdiomas(){
    return idiomas;
}


public List<ProduccionB> getProduccionesBibliograficas(){
    return produccionesBibliograficas;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
}


public List<GruposInves> getGrupos(){
    return grupos;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Investigador other = (Investigador) obj;
    if (id != other.id)
        return false;
    return true;
}


public void setId(long id){
    this.id = id;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setProduccionesBibliograficas(List<ProduccionB> produccionesBibliograficas){
    this.produccionesBibliograficas = produccionesBibliograficas;
}


public List<Produccion> getProducciones(){
  this.producciones = produccionrequest.getProducciones(this.id);
return this.producciones;
}


public String getCategoria(){
    return categoria;
}


public List<LineasInvestigacion> getLineasInvestigacion(){
    return lineasInvestigacion;
}


}