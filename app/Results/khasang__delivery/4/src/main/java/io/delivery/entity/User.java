package io.delivery.entity;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence;
@Entity
@Table(name = "users")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String login;

 private  String password;

 private  String role;

 private  Boolean active;

public User() {
}
public String getLogin(){
    return login;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setActive(Boolean active){
    this.active = active;
}


public void EncodePassword(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    this.password = bCryptPasswordEncoder.encode(this.password);
}


public void setLogin(String login){
    this.login = login;
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


public Boolean getActive(){
    return active;
}


}