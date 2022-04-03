package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.FileService;
public class FileServiceImpl implements FileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String getAvatarImgUrl(Long fileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAvatarImgUrl"))
    .queryParam("fileId",fileId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}