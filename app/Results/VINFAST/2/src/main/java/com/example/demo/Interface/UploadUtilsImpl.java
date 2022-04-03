package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.UploadUtils;
public class UploadUtilsImpl implements UploadUtils{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void uploadProduct(String fileName,MultipartFile multipartFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadProduct"))
    .queryParam("fileName",fileName)
    .queryParam("multipartFile",multipartFile)
;
  restTemplate.put(builder.toUriString(), null);
}


}