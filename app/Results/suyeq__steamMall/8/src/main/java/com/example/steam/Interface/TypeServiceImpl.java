package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.TypeService;
public class TypeServiceImpl implements TypeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<String> findAllType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllType"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public int deleteTypeById(long typeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteTypeById"))
    .queryParam("typeId",typeId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int addType(String typeName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addType"))
    .queryParam("typeName",typeName)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}