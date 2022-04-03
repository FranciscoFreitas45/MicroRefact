package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.DeleteUserAccountsWorker;
public class DeleteUserAccountsWorkerImpl implements DeleteUserAccountsWorker{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void run(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/run"))
;
  restTemplate.put(builder.toUriString(), null);
}


}