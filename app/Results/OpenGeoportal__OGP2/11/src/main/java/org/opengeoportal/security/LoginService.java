package org.opengeoportal.security;
 public interface LoginService {


public LoginStatus logoutResponse()
;

public LoginStatus getStatus()
;

public LoginStatus login(String username,String password)
;

}