package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.ImageService;
public class ImageServiceImpl implements ImageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Long addImage(Image image){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addImage"))
    .queryParam("image",image)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public String findImageUrlById(long id,String dataSource){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findImageUrlById"))
    .queryParam("id",id)
    .queryParam("dataSource",dataSource)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Image findImageById(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findImageById"))
    .queryParam("id",id)
;  Image aux = restTemplate.getForObject(builder.toUriString(), Image.class);

 return aux;
}


}