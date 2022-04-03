package run.halo.app.security.authentication;
 import run.halo.app.security.support.UserDetail;
public class AuthenticationImpl implements Authentication{

 private  UserDetail userDetail;

public AuthenticationImpl(UserDetail userDetail) {
    this.userDetail = userDetail;
}
@Override
public UserDetail getDetail(){
    return userDetail;
}


}