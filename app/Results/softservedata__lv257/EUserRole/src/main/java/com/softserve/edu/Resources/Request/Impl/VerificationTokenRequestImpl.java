package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.VerificationToken;
import com.softserve.edu.Resources.Request.VerificationTokenRequest;
public class VerificationTokenRequestImpl implements VerificationTokenRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public VerificationToken getVerificationToken(Long id69IL){
 VerificationToken aux = restTemplate.getForObject("http://2/User/{id}/VerificationToken/getVerificationToken",VerificationToken.class,id69IL);
return aux;
}


public User setVerificationToken(VerificationToken verificationToken,Long id69IL){
 User aux = restTemplate.getForObject("http://2/User/{id}/VerificationToken/setVerificationToken?'verificationToken'=verificationToken&'id69IL'=id69IL',",User.class,id69IL);
return aux;
}


}