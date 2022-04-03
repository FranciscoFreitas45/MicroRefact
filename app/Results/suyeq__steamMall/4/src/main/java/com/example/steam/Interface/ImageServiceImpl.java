package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.ImageService;
public class ImageServiceImpl implements ImageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public String findImageUrlById(long id,String dataSource){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findImageUrlById"))
    .queryParam("id",id)
    .queryParam("dataSource",dataSource)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public List<String> findGameImageUrlsByGameId(long gameId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGameImageUrlsByGameId"))
    .queryParam("gameId",gameId)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}