package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.FileUploadUtil;
public class FileUploadUtilImpl implements FileUploadUtil{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public String handleMultipartFile(MultipartFile file){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/handleMultipartFile"))
    .queryParam("file",file)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}