package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity(name = "USER_ROLE")
@Table(name = "USER_ROLE", schema = "gri")
public class Rol implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "ID", length = 300)
 private  long id;

@Column(name = "ROLE", length = 300)
 private  String role;

@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<User> users;

/**
 * Instantiates a new rol.
 */
public Rol() {
}/**
 * Instantiates a new rol.
 *
 * @param id the id
 * @param role the role
 * @param users the users
 */
public Rol(long id, String role, List<User> users) {
    this.id = id;
    this.role = role;
    this.users = users;
}
public void setUsers(List<User> users){
    this.users = users;
}


public List<User> getUsers(){
    return users;
}


public void setRole(String role){
    this.role = role;
}


public String getRole(){
    return role;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


}