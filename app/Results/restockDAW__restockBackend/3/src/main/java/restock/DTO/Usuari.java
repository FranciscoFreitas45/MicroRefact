package restock.DTO;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class Usuari {

 private  long serialVersionUID;

 private  Integer id;

 private  String user;

 private  String password;

 private  String nom;

 private  String cognom1;

 private  String cognom2;

 private  String nif;

 private  Date dataNaixement;

 private  Organitzacio organitzacio;

 private  Rol rol;

 private  String correu;

/**
 * Usuari.
 */
public Usuari() {
}/**
 * Usuari.
 *
 * @param id the id
 */
public Usuari(final Integer id) {
    super();
    this.id = id;
}
@Column(name = "user", nullable = false)
public String getUser(){
    return user;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


@Column(name = "correu", nullable = false)
public String getCorreu(){
    return correu;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "organitzacio_id")
public Organitzacio getOrganitzacio(){
    return organitzacio;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "rol_id", nullable = false)
public Rol getRol(){
    return rol;
}


@Column(name = "password", nullable = false)
public String getPassword(){
    return password;
}


@Column(name = "cognom2", nullable = true)
public String getCognom2(){
    return cognom2;
}


@Column(name = "cognom1", nullable = true)
public String getCognom1(){
    return cognom1;
}


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
@Column(name = "data_naixement", nullable = true)
public Date getDataNaixement(){
    return dataNaixement;
}


@Column(name = "nif", nullable = false)
public String getNif(){
    return nif;
}


@Column(name = "nom", nullable = true)
public String getNom(){
    return nom;
}


}