package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void logActivity(Account account,ActivityType action,Object actionOn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/logActivity"))
    .queryParam("account",account)
    .queryParam("action",action)
    .queryParam("actionOn",actionOn)
;
  restTemplate.put(builder.toUriString(), null);
}


}