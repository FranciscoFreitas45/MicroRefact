package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersListCreditService;
public class ICreepersListCreditServiceImpl implements ICreepersListCreditService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void updateImageAndHtmlPath(String loginName,String imagePath,String filePath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateImageAndHtmlPath"))
    .queryParam("loginName",loginName)
    .queryParam("imagePath",imagePath)
    .queryParam("filePath",filePath)
;
  restTemplate.put(builder.toUriString(), null);
}


}