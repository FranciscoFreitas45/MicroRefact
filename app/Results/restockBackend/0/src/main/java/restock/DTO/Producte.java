package restock.DTO;
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


public String getModel(){
    return model;
}



public SubFamilia getSubfamilia(){
    return subfamilia;
}



public Integer getId(){
    return id;
}


public Double getPreu(){
    return preu;
}


public String getMarca(){
    return marca;
}



public Proveidor getProveidor(){
    return proveidor;
}


public String getDescripcio(){
    return descripcio;
}


public void setModel(String model){
    this.model = model;
}


public void setPreu(Double preu){
    this.preu = preu;
}


public Double getPreuVenda(){
    return preuVenda;
}


}