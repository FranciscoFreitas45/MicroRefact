package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.FileService;
public class FileServiceImpl implements FileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Attachment uploadFileToOSS(String base64Str,String fileName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadFileToOSS"))
    .queryParam("base64Str",base64Str)
    .queryParam("fileName",fileName)
;  Attachment aux = restTemplate.getForObject(builder.toUriString(), Attachment.class);

 return aux;
}


}