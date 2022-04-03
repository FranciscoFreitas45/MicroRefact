package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.EmailHelper;
public class EmailHelperImpl implements EmailHelper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void sendAccountActivationEmail(User user,UserToken userToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendAccountActivationEmail"))
    .queryParam("user",user)
    .queryParam("userToken",userToken)
;
  restTemplate.put(builder.toUriString(), null);
}


}