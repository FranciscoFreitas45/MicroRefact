package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "comanda")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Comanda {

 private  long serialVersionUID;

 private  Integer id;

 private  Date datacomanda;

 private  Botiga botiga;

 private  Proveidor proveidor;

 private  Date datarecepcio;


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "proveidor_id", nullable = false)
public Proveidor getProveidor(){
    return proveidor;
}


public void setDatacomanda(Date datacomanda){
    this.datacomanda = datacomanda;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "botiga_id", nullable = false)
public Botiga getBotiga(){
    return botiga;
}


public void setDatarecepcio(Date datarecepcio){
    this.datarecepcio = datarecepcio;
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


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
@Column(name = "datarecepcio", nullable = true)
public Date getDatarecepcio(){
    return datarecepcio;
}


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
@Column(name = "datacomanda", nullable = false)
public Date getDatacomanda(){
    return datacomanda;
}


public void setProveidor(Proveidor proveidor){
    this.proveidor = proveidor;
}


}