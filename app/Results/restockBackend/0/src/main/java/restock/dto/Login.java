package restock.dto;
 public class Login {

 private  long serialVersionUID;

 private  String user;

 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getUser(){
    return user;
}


public void setUser(String user){
    this.user = user;
}


}