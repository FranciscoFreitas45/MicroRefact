package lk.sliit.csse.procurementsystem.models;
 import javax.persistence;
@Entity
@Inheritance
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Integer id;

 private  String email;

 private  String password;

 private  boolean enabled;


public void setPassword(String password){
    this.password = password;
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public boolean isEnabled(){
    return enabled;
}


public String getEmail(){
    return email;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}