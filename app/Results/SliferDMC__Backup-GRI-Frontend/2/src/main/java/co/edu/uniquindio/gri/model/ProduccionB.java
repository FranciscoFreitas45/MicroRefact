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
@Entity(name = "PRODUCCIONBIBLIOGRAFICA")
@Table(name = "BIBLIOGRAFICAS", schema = "gri")
public class ProduccionB implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
 private  long id;

@Column(name = "IDENTIFICADOR", length = 400)
 private  String identificador;

@Column(name = "AUTORES", length = 2000)
 private  String autores;

@Column(name = "ANIO", length = 10)
 private  String anio;

@Column(name = "REFERENCIA", length = 4000)
 private  String referencia;

@Column(name = "REPETIDO", length = 10)
 private  String repetido;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "TIPO_ID")
@JsonIgnore
 private  Tipo tipo;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "INVESTIGADORES_ID")
@JsonIgnore
 private  Investigador investigador;

/**
 * Instantiates a new produccion B
 *
 * @param id the id
 * @param identificador the identificador
 * @param autores the autores
 * @param anio the anio
 * @param referencia the referencia
 * @param tipo the tipo
 * @param repetido the repetido
 * @param investigador the investigador
 * @param estado the estado
 */
public ProduccionB(long id, String identificador, String autores, String anio, String referencia, Tipo tipo, String repetido, Investigador investigador) {
    this.id = id;
    this.identificador = identificador;
    this.autores = autores;
    this.anio = anio;
    this.referencia = referencia;
    this.tipo = tipo;
    this.repetido = repetido;
    this.investigador = investigador;
}/**
 * Instantiates a new produccion B.
 */
public ProduccionB() {
}
public Investigador getInvestigador(){
    return investigador;
}


public void setIdentificador(String identificador){
    this.identificador = identificador;
}


public String getReferencia(){
    return referencia;
}


public void setReferencia(String referencia){
    this.referencia = referencia;
}


public String getIdentificador(){
    return identificador;
}


public void setTipo(Tipo tipo){
    this.tipo = tipo;
}


public String getRepetido(){
    return repetido;
}


public long getId(){
    return id;
}


public void setInvestigador(Investigador investigador){
    this.investigador = investigador;
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
    ProduccionB other = (ProduccionB) obj;
    if (id != other.id)
        return false;
    return true;
}


public void setRepetido(String repetido){
    this.repetido = repetido;
}


public void setId(long id){
    this.id = id;
}


public String getAutores(){
    return autores;
}


}