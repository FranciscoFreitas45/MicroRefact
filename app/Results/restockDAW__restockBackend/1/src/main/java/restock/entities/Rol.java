package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "rol")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Rol {

 private  long serialVersionUID;

 private  Integer id;

 private  String tipus;

 private  String descripcio;

/**
 * Rol.
 */
public Rol() {
}/**
 * Rol.
 *
 * @param id the id
 */
public Rol(final Integer id) {
    super();
    this.id = id;
}
@Column(name = "tipus", nullable = false)
public String getTipus(){
    return tipus;
}


@Column(name = "descripcio", nullable = false)
public String getDescripcio(){
    return descripcio;
}


public void setTipus(String tipus){
    this.tipus = tipus;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


public void setDescripcio(String descripcio){
    this.descripcio = descripcio;
}


}