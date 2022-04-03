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
@Table(name = "inventari")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Inventari {

 private  long serialVersionUID;

 private  Integer id;

 private  Double quantitat;

 private  Producte producte;

 private  Botiga botiga;


public void setProducte(Producte producte){
    this.producte = producte;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "botiga_id", nullable = false)
public Botiga getBotiga(){
    return botiga;
}


public void setBotiga(Botiga botiga){
    this.botiga = botiga;
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


@Column(name = "quantitat", nullable = false)
public Double getQuantitat(){
    return quantitat;
}


public void setQuantitat(Double quantitat){
    this.quantitat = quantitat;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "producte_id", nullable = false)
public Producte getProducte(){
    return producte;
}


}