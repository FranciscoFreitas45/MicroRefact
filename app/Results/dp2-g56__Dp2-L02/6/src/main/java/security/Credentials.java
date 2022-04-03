package security;
 import javax.validation.constraints.Size;
public class Credentials {

 private  String username;

 private  String password;

// Constructors -----------------------------------------------------------
public Credentials() {
    super();
}
public void setJ_username(String username){
    this.username = username;
}


public void setPassword(String password){
    this.password = password;
}


@Size(min = 5, max = 32)
public String getPassword(){
    return this.password;
}


@Size(min = 5, max = 32)
public String getUsername(){
    return this.username;
}


}