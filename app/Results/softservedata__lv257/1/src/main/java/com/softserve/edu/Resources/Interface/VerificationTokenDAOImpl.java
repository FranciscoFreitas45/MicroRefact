package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.VerificationTokenDAO;
public class VerificationTokenDAOImpl implements VerificationTokenDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public VerificationToken makePersistent(VerificationToken verificationToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/makePersistent"))
    .queryParam("verificationToken",verificationToken)
;  VerificationToken aux = restTemplate.getForObject(builder.toUriString(), VerificationToken.class);

 return aux;
}


public VerificationToken findByToken(String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByToken"))
    .queryParam("token",token)
;  VerificationToken aux = restTemplate.getForObject(builder.toUriString(), VerificationToken.class);

 return aux;
}


public void makeTransient(VerificationToken verificationToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/makeTransient"))
    .queryParam("verificationToken",verificationToken)
;
  restTemplate.put(builder.toUriString(), null);
}


}