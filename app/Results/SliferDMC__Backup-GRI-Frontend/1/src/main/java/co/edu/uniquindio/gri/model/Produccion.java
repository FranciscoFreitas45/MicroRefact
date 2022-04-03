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
import co.edu.uniquindio.gri.Request.TipoRequest;
import co.edu.uniquindio.gri.Request.Impl.TipoRequestImpl;
import co.edu.uniquindio.gri.DTO.Tipo;
import co.edu.uniquindio.gri.Request.InvestigadorRequest;
import co.edu.uniquindio.gri.Request.Impl.InvestigadorRequestImpl;
import co.edu.uniquindio.gri.DTO.Investigador;
@Entity(name = "PRODUCCIONES")
@Table(name = "PRODUCCIONES", schema = "gri")
public class Produccion implements Serializable{

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

@Column(name = "REPETIDO", length = 10)
 private  String repetido;

@Transient
 private  Tipo tipo;

@Transient
 private  Investigador investigador;

@Column(name = "id")
 private long id;

@Transient
 private TipoRequest tiporequest = new TipoRequestImpl();;

@Column(name = "id")
 private long id;

@Transient
 private InvestigadorRequest investigadorrequest = new InvestigadorRequestImpl();;

/**
 * Instantiates a new produccion.
 *
 * @param id the id
 * @param autores the autores
 * @param anio the anio
 * @param referencia the referencia
 * @param tipo the tipo
 * @param repetido the repetido
 * @param tipoProduccion the tipo produccion
 * @param investigador the investigador
 * @param estado the estado
 */
public Produccion(long id, String autores, String anio, String referencia, Tipo tipo, String repetido, TipoProduccion tipoProduccion, Investigador investigador) {
    this.id = id;
    this.autores = autores;
    this.anio = anio;
    this.referencia = referencia;
    this.tipo = tipo;
    this.investigador = investigador;
}/**
 * Instantiates a new produccion.
 */
public Produccion() {
}
public Investigador getInvestigador(){
  this.investigador = investigadorrequest.getInvestigador(this.id);
return this.investigador;
}


public String getReferencia(){
    return referencia;
}


public void setReferencia(String referencia){
    this.referencia = referencia;
}


public void setTipo(Tipo tipo){
 tiporequest.setTipo(tipo,this.id);
}



public String getRepetido(){
    return repetido;
}


public long getId(){
    return id;
}


public void setInvestigador(Investigador investigador){
 investigadorrequest.setInvestigador(investigador,this.id);
}



public Tipo getTipo(){
  this.tipo = tiporequest.getTipo(this.id);
return this.tipo;
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
    Produccion other = (Produccion) obj;
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