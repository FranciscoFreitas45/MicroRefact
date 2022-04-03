package ar.com.veterinaria.app.entities.user;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ar.com.veterinaria.app.entities.Address;
@Entity
@Table(name = "Client")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Client extends Personimplements Serializable{

 private  long serialVersionUID;

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idClient", unique = true, nullable = false)
@JsonIgnore
 private  Integer idClient;

public Client() {
    super();
}public Client(Integer idPerson, String name, String surname, Integer dni, String birthday, Integer telephone, Integer cellphone, Address address, Integer idClient) {
    super(idPerson, name, surname, dni, birthday, telephone, cellphone, address);
    this.idClient = idClient;
}public Client(Integer idPerson, String name, String surname, Integer dni, String birthday, Integer cellphone, Address address, Integer idClient) {
    super(idPerson, name, surname, dni, birthday, cellphone, address);
    this.idClient = idClient;
}
public Integer getIdClient(){
    return idClient;
}


public void setIdClient(Integer idClient){
    this.idClient = idClient;
}


}