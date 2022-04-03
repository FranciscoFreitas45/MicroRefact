package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity(name = "USERS")
@Table(name = "USERS", schema = "gri")
public class User implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID", length = 300)
 private  long id;

@Column(name = "USERNAME", length = 300)
 private  String username;

@Column(name = "PASSWORD", length = 300)
 private  String password;

@ManyToOne()
@JoinColumn(name = "USER_ROLE_ID")
 private  Rol rol;

/**
 * Instantiates a new user.
 */
public User() {
}/**
 * Instantiates a new user.
 *
 * @param id the id
 * @param username the username
 * @param password the password
 */
public User(long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
}/**
 * Instantiates a new user.
 *
 * @param user the user
 */
public User(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
}
public void setPassword(String password){
    this.password = password;
}


public Rol getRol(){
    return rol;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public void setRol(Rol rol){
    this.rol = rol;
}


}