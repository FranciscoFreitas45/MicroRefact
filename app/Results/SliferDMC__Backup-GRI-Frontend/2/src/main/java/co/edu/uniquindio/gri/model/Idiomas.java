package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name = "IDIOMAS")
@Table(name = "IDIOMAS", schema = "gri")
public class Idiomas implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
@JsonIgnore
 private  long id;

@Column(name = "IDIOMA")
 private  String idioma;

@Column(name = "HABLA")
 private  String habla;

@Column(name = "ESCRIBE")
 private  String escribe;

@Column(name = "LEE")
 private  String lee;

@Column(name = "ENTIENDE")
 private  String entiende;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "INVESTIGADORES_ID")
@JsonIgnore
 private  Investigador investigador;

/**
 * Instantiates a new idiomas.
 *
 * @param id the id
 * @param idioma the idioma
 * @param habla the habla
 * @param escribe the escribe
 * @param lee the lee
 * @param entiende the entiende
 * @param investigador the investigador
 */
public Idiomas(long id, String idioma, String habla, String escribe, String lee, String entiende, Investigador investigador) {
    this.id = id;
    this.idioma = idioma;
    this.habla = habla;
    this.escribe = escribe;
    this.lee = lee;
    this.entiende = entiende;
    this.investigador = investigador;
}/**
 * Instantiates a new idiomas.
 */
public Idiomas() {
}
public Investigador getInvestigador(){
    return investigador;
}


public void setLee(String lee){
    this.lee = lee;
}


public long getId(){
    return id;
}


public void setEntiende(String entiende){
    this.entiende = entiende;
}


public String getHabla(){
    return habla;
}


public void setInvestigador(Investigador investigador){
    this.investigador = investigador;
}


public void setHabla(String habla){
    this.habla = habla;
}


public String getEscribe(){
    return escribe;
}


public void setEscribe(String escribe){
    this.escribe = escribe;
}


public void setId(long id){
    this.id = id;
}


public String getEntiende(){
    return entiende;
}


public void setIdioma(String idioma){
    this.idioma = idioma;
}


public String getIdioma(){
    return idioma;
}


public String getLee(){
    return lee;
}


}