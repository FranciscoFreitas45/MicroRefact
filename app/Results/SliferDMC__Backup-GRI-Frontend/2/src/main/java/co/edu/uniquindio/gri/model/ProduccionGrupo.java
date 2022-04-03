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
@Entity(name = "PRODUCCIONESG")
@Table(name = "PRODUCCIONESG", schema = "gri")
public class ProduccionGrupo implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
 private  long id;

@Column(name = "AUTORES", length = 2000)
 private  String autores;

@Column(name = "ANIO", length = 10)
 private  String anio;

@Column(name = "REFERENCIA", length = 4000)
 private  String referencia;

@Column(name = "REPETIDO", length = 20)
 private  String repetido;

@Column(name = "INVENTARIO")
 private  int estado;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "TIPO_ID")
@JsonIgnore
 private  Tipo tipo;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "GRUPOS_ID")
@JsonIgnore
 private  Grupo grupo;

/**
 * Instantiates a new produccion grupo.
 *
 * @param id the id
 * @param autores the autores
 * @param anio the anio
 * @param referencia the referencia
 * @param tipo the tipo
 * @param repetido the repetido
 * @param tipoProduccion the tipo produccion
 * @param grupo the grupo
 * @param estado the estado
 */
public ProduccionGrupo(long id, String autores, String anio, String referencia, Tipo tipo, String repetido, TipoProduccion tipoProduccion, Grupo grupo, int estado) {
    this.id = id;
    this.autores = autores;
    this.anio = anio;
    this.referencia = referencia;
    this.tipo = tipo;
    this.grupo = grupo;
    this.estado = estado;
}/**
 * Instantiates a new produccion grupo.
 *
 * @param id the id
 * @param autores the autores
 * @param anio the anio
 * @param referencia the referencia
 */
public ProduccionGrupo(long id, String autores, String anio, String referencia) {
    this.id = id;
    this.autores = autores;
    this.anio = anio;
    this.referencia = referencia;
}/**
 * Instantiates a new produccion grupo.
 */
public ProduccionGrupo() {
}
public String getReferencia(){
    return referencia;
}


public void setReferencia(String referencia){
    this.referencia = referencia;
}


public void setTipo(Tipo tipo){
    this.tipo = tipo;
}


public String getRepetido(){
    return repetido;
}


public int getEstado(){
    return estado;
}


public long getId(){
    return id;
}


public void setGrupo(Grupo grupo){
    this.grupo = grupo;
}


public Grupo getGrupo(){
    return grupo;
}


public Tipo getTipo(){
    return tipo;
}


public void setAnio(String anio){
    this.anio = anio;
}


public String getAnio(){
    return anio;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
}


public void setAutores(String autores){
    this.autores = autores;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ProduccionGrupo other = (ProduccionGrupo) obj;
    if (id != other.id)
        return false;
    return true;
}


public void setRepetido(String repetido){
    this.repetido = repetido;
}


public void setEstado(int estado){
    this.estado = estado;
}


public void setId(long id){
    this.id = id;
}


public String getAutores(){
    return autores;
}


}