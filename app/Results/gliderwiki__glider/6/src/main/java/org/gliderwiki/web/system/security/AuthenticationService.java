package org.gliderwiki.web.system.security;
 import org.gliderwiki.framework.exception.AuthenticationNotException;
import org.gliderwiki.framework.exception.PasswordMismatchException;
import org.gliderwiki.framework.exception.UserNotFoundException;
public interface AuthenticationService {


public void login(String userId,String password)
;

}