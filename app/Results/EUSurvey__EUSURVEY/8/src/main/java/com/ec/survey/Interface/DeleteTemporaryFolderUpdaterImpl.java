package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.DeleteTemporaryFolderUpdater;
public class DeleteTemporaryFolderUpdaterImpl implements DeleteTemporaryFolderUpdater{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void run(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/run"))
;
  restTemplate.put(builder.toUriString(), null);
}


}