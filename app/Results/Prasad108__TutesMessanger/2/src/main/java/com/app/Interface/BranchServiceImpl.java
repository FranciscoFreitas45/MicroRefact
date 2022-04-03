package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.BranchService;
public class BranchServiceImpl implements BranchService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Branch> getallOfParticularInstitute(Institute id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallOfParticularInstitute"))
    .queryParam("id",id)
;  List<Branch> aux = restTemplate.getForObject(builder.toUriString(), List<Branch>.class);

 return aux;
}


public void create(Branch branch){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("branch",branch)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Branch branch){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("branch",branch)
;
  restTemplate.put(builder.toUriString(), null);
}


public void delet(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delet"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public Branch find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Branch aux = restTemplate.getForObject(builder.toUriString(), Branch.class);

 return aux;
}


}