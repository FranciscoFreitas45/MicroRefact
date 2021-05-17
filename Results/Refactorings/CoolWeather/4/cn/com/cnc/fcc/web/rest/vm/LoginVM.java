import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class LoginVM {

@NotNull
@Size(min = 1, max = 50)
 private  String username;

@NotNull
@Size(min = ManagedUserVM.PASSWORD_MIN_LENGTH, max = ManagedUserVM.PASSWORD_MAX_LENGTH)
 private  String password;

 private  Boolean rememberMe;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public void setRememberMe(Boolean rememberMe){
    this.rememberMe = rememberMe;
}


public Boolean isRememberMe(){
    return rememberMe;
}


@Override
public String toString(){
    return "LoginVM{" + "username='" + username + '\'' + ", rememberMe=" + rememberMe + '}';
}


public String getUsername(){
    return username;
}


}