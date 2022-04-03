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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import co.edu.uniquindio.gri.Request.CentroRequest;
import co.edu.uniquindio.gri.Request.Impl.CentroRequestImpl;
import co.edu.uniquindio.gri.DTO.Centro;
@Entity(name = "GRUPOS")
@Table(name = "GRUPOS", schema = "gri")
public class Grupo implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID", length = 300)
 private  long id;

@Column(name = "NOMBRE", length = 300)
 private  String nombre;

@Column(name = "AREACONOCIMIENTO", length = 300)
 private  String areaConocimiento;

@Column(name = "ANIOFUNDACION", length = 300)
 private  String anioFundacion;

@Column(name = "LIDER", length = 300)
 private  String lider;

@Column(name = "CATEGORIA", length = 300)
 private  String categoria;

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "GRUPOS_LINEAS", joinColumns = { @JoinColumn(name = "GRUPOS_ID") }, inverseJoinColumns = { @JoinColumn(name = "LINEASINVESTIGACION_ID") }, schema = "gri")
@JsonIgnore
 private  List<LineasInvestigacion> lineasInvestigacion;

@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionGrupo> produccion;

@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
 private  List<ProduccionBGrupo> produccionBibliografica;

@Transient
 private  Centro centro;

@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinTable(name = "PROGRAMAS_GRUPOS", joinColumns = { @JoinColumn(name = "GRUPOS_ID") }, inverseJoinColumns = { @JoinColumn(name = "PROGRAMAS_ID") }, schema = "gri")
 private  List<Programa> programas;

@OneToMany(mappedBy = "grupos", cascade = CascadeType.MERGE)
@JsonIgnore
 private  List<GruposInves> investigadores;

@Column(name = "id")
 private long id;

@Transient
 private CentroRequest centrorequest = new CentroRequestImpl();;

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


public void removePrograma(Programa programa){
    programas.remove(programa);
    programa.getGrupos().remove(this);
}


@JsonIgnore
public List<GruposInves> getInvestigadores(){
    return investigadores;
}


public String getAnioFundacion(){
    return anioFundacion;
}


public void setAreaConocimiento(String areaConocimiento){
    this.areaConocimiento = areaConocimiento;
}


public void removeInvestigador(Investigador investigador){
    investigadores.remove(investigador);
    investigador.getGrupos().remove(this);
}


public Centro getCentro(){
  this.centro = centrorequest.getCentro(this.id);
return this.centro;
}


public List<ProduccionGrupo> getProduccion(){
    return produccion;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
}


public void setCentro(Centro centro){
 centrorequest.setCentro(centro,this.id);
}



public void setId(long id){
    this.id = id;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setProduccionBibliografica(List<ProduccionBGrupo> produccionBibliografica){
    this.produccionBibliografica = produccionBibliografica;
}


public void setAnioFundacion(String anioFundacion){
    this.anioFundacion = anioFundacion;
}


public void setInvestigadores(List<GruposInves> investigadores){
    this.investigadores = investigadores;
}


public String getAreaConocimiento(){
    return areaConocimiento;
}


public String getNombre(){
    return nombre;
}


public void setLineasInvestigacion(List<LineasInvestigacion> lineasInvestigacion){
    this.lineasInvestigacion = lineasInvestigacion;
}


public void setCategoria(String categoria){
    this.categoria = categoria;
}


public void removeLineasInvestigacion(LineasInvestigacion lineas){
    lineasInvestigacion.remove(lineas);
    lineas.getGrupos().remove(this);
}


public List<Programa> getProgramas(){
    return programas;
}


public void setProduccion(List<ProduccionGrupo> produccion){
    this.produccion = produccion;
}


public void setProgramas(List<Programa> programas){
    this.programas = programas;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Grupo other = (Grupo) obj;
    if (id != other.id)
        return false;
    return true;
}


public List<ProduccionBGrupo> getProduccionBibliografica(){
    return produccionBibliografica;
}


public void setLider(String lider){
    this.lider = lider;
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