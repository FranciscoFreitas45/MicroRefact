package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.FileService;
public class FileServiceImpl implements FileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public boolean isValid(FileType fileType,MultipartFile multipartFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isValid"))
    .queryParam("fileType",fileType)
    .queryParam("multipartFile",multipartFile)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public String uploadLocal(FileType fileType,MultipartFile multipartFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadLocal"))
    .queryParam("fileType",fileType)
    .queryParam("multipartFile",multipartFile)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}