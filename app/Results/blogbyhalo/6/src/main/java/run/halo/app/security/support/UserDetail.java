package run.halo.app.security.support;
 import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;
import run.halo.app.exception.AuthenticationException;
import run.halo.app.model.entity.User;
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

 private  User user;


@NonNull
public User getUser(){
    return user;
}


public void setUser(User user){
    this.user = user;
}


}