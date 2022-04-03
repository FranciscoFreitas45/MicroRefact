package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "subfamilia")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubFamilia {

 private  long serialVersionUID;

 private  Integer id;

 private  String nomSubfamilia;

 private  String descripcio;

 private  Familia familia;

/**
 * Sub familia.
 */
public SubFamilia() {
}/**
 * Sub familia.
 *
 * @param id the id
 */
public SubFamilia(final Integer id) {
    super();
    this.id = id;
}
public void setNomSubfamilia(String nomSubfamilia){
    this.nomSubfamilia = nomSubfamilia;
}


@Column(name = "descripcio", nullable = true)
public String getDescripcio(){
    return descripcio;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "familia_id", nullable = false)
public Familia getFamilia(){
    return familia;
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


public void setFamilia(Familia familia){
    this.familia = familia;
}


@Column(name = "nomSubfamilia", nullable = false)
public String getNomSubfamilia(){
    return nomSubfamilia;
}


}