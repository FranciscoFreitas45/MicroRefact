package restock.DTO;
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
public class Producte {

 private  long serialVersionUID;

 private  Integer id;

 private  String marca;

 private  String model;

 private  String descripcio;

 private  Double preu;

 private  Double preuVenda;

 private  SubFamilia subfamilia;

 private  Proveidor proveidor;

/**
 * Producte.
 */
public Producte() {
}/**
 * Producte.
 *
 * @param id the id
 */
public Producte(final Integer id) {
    super();
    this.id = id;
}
@Column(name = "model", nullable = false)
public String getModel(){
    return model;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "subfamilia_id", nullable = false)
public SubFamilia getSubfamilia(){
    return subfamilia;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


@Column(name = "preu", nullable = false)
public Double getPreu(){
    return preu;
}


@Column(name = "marca", nullable = false)
public String getMarca(){
    return marca;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "proveidor_id", nullable = false)
public Proveidor getProveidor(){
    return proveidor;
}


@Column(name = "descripcio", nullable = false)
public String getDescripcio(){
    return descripcio;
}


public void setModel(String model){
    this.model = model;
}


public void setPreu(Double preu){
    this.preu = preu;
}


@Column(name = "preuVenda", nullable = false)
public Double getPreuVenda(){
    return preuVenda;
}


}