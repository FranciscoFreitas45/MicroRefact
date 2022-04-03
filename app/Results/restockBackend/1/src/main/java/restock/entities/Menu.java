package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Menu {

 private  long serialVersionUID;

 private  Integer id;

 private  String descripcio;


@Column(name = "descripcio", nullable = false)
public String getDescripcio(){
    return descripcio;
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