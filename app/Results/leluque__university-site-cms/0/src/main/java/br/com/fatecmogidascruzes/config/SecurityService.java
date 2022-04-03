package br.com.fatecmogidascruzes.config;
 import javax.servlet.http.HttpServletRequest;
public interface SecurityService {


public void autologin(HttpServletRequest request,String name,String password)
;

public String findByUserName()
;

}