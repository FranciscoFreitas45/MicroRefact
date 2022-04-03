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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Grupo implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String nombre;

 private  String areaConocimiento;

 private  String anioFundacion;

 private  String lider;

 private  String categoria;

 private  List<LineasInvestigacion> lineasInvestigacion;

 private  List<ProduccionGrupo> produccion;

 private  List<ProduccionBGrupo> produccionBibliografica;

 private  Centro centro;

 private  List<Programa> programas;

 private  List<GruposInves> investigadores;

/**
 * Instantiates a new grupo.
 *
 * @param id the id
 * @param nombre the nombre
 * @param areaConocimiento the area conocimiento
 * @param anioFundacion the anio fundacion
 * @param lider the lider
 * @param categoria the categoria
 * @param lineasInvestigacion the lineas investigacion
 * @param produccion the produccion
 * @param produccionBibliografica the produccion bibliografica
 * @param centro the centro
 */
public Grupo(long id, String nombre, String areaConocimiento, String anioFundacion, String lider, String categoria, List<LineasInvestigacion> lineasInvestigacion, List<ProduccionGrupo> produccion, List<ProduccionBGrupo> produccionBibliografica, Centro centro, List<GruposInves> investigadores) {
    this.id = id;
    this.nombre = nombre;
    this.areaConocimiento = areaConocimiento;
    this.anioFundacion = anioFundacion;
    this.lider = lider;
    this.categoria = categoria;
    this.lineasInvestigacion = lineasInvestigacion;
    this.produccion = produccion;
    this.produccionBibliografica = produccionBibliografica;
    this.centro = centro;
    this.investigadores = investigadores;
}/**
 * Instantiates a new grupo.
 *
 * @param id the id
 * @param nombre the nombre
 * @param categoria the categoria
 * @param lider the lider
 */
public Grupo(long id, String nombre, String categoria, String lider) {
    this.id = id;
    this.nombre = nombre;
    this.lider = lider;
    this.categoria = categoria;
}/**
 * Instantiates a new grupo.
 */
public Grupo() {
}
public long getId(){
    return id;
}


@JsonIgnore
public List<GruposInves> getInvestigadores(){
    return investigadores;
}


public String getAnioFundacion(){
    return anioFundacion;
}


public Centro getCentro(){
    return centro;
}


public List<ProduccionGrupo> getProduccion(){
    return produccion;
}


public String getAreaConocimiento(){
    return areaConocimiento;
}


public String getNombre(){
    return nombre;
}


public List<Programa> getProgramas(){
    return programas;
}


public List<ProduccionBGrupo> getProduccionBibliografica(){
    return produccionBibliografica;
}


public String getLider(){
    return lider;
}


public String getCategoria(){
    return categoria;
}


public List<LineasInvestigacion> getLineasInvestigacion(){
    return lineasInvestigacion;
}


}