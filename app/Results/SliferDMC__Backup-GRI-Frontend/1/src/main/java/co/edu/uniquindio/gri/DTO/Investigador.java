package co.edu.uniquindio.gri.DTO;
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
public class Investigador implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  String categoria;

 private  String nivelAcademico;

 private  String pertenencia;

 private  List<Idiomas> idiomas;

 private  List<LineasInvestigacion> lineasInvestigacion;

 private  List<Produccion> producciones;

 private  List<ProduccionB> produccionesBibliograficas;

 private  List<GruposInves> grupos;

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
public String getNombre(){
    return nombre;
}


public long getId(){
    return id;
}


public String getNivelAcademico(){
    return nivelAcademico;
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


public List<GruposInves> getGrupos(){
    return grupos;
}


public List<Produccion> getProducciones(){
    return producciones;
}


public String getCategoria(){
    return categoria;
}


public List<LineasInvestigacion> getLineasInvestigacion(){
    return lineasInvestigacion;
}


}