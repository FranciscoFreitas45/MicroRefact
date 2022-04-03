package com.lingxiang2014.service;
 import java.security.interfaces.RSAPublicKey;
import javax.servlet.http.HttpServletRequest;
public interface RSAService {


public RSAPublicKey generateKey(HttpServletRequest request)
;

public void removePrivateKey(HttpServletRequest request)
;

public String decryptParameter(String name,HttpServletRequest request)
;

}