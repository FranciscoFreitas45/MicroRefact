package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ClassesService;
public class ClassesServiceImpl implements ClassesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Classes> getallOfParticularBranch(Branch branch){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallOfParticularBranch"))
    .queryParam("branch",branch)
;  List<Classes> aux = restTemplate.getForObject(builder.toUriString(), List<Classes>.class);

 return aux;
}


public void create(Classes classes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("classes",classes)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Classes classes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("classes",classes)
;
  restTemplate.put(builder.toUriString(), null);
}


public void delet(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delet"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public Classes find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Classes aux = restTemplate.getForObject(builder.toUriString(), Classes.class);

 return aux;
}


}