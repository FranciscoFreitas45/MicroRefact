package cn.com.cnc.fcc.web.rest.vm;
 import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class LoginPAPI {

@NotNull
@Size(min = 1, max = 50)
 private  String username;

@NotNull
@Size(min = ManagedUserVM.PASSWORD_MIN_LENGTH, max = ManagedUserVM.PASSWORD_MAX_LENGTH)
 private  String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


@Override
public String toString(){
    return "LoginPAPI{" + "username='" + username + '\'' + '}';
}


public String getUsername(){
    return username;
}


}