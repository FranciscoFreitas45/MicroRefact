import lombok.Getter;
@Getter
public class LoginForm {

 private  String mail;

 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setMail(String mail){
    this.mail = mail;
}


public String getMail(){
    return mail;
}


}