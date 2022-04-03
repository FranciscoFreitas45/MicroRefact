package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "familia")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Familia {

 private  long serialVersionUID;

 private  Integer id;

 private  String nomFamilia;

 private  String descripcio;

/**
 * Familia.
 */
public Familia() {
}/**
 * Familia.
 *
 * @param id the id
 */
public Familia(final Integer id) {
    super();
    this.id = id;
}
@Column(name = "nomFamilia", nullable = false)
public String getNomFamilia(){
    return nomFamilia;
}


@Column(name = "descripcio", nullable = true)
public String getDescripcio(){
    return descripcio;
}


public void setNomFamilia(String nomFamilia){
    this.nomFamilia = nomFamilia;
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