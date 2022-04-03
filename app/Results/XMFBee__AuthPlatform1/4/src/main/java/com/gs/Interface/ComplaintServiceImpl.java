package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.ComplaintService;
public class ComplaintServiceImpl implements ComplaintService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object queryByPager(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int countComplaintUser(String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countComplaintUser"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Complaint> queryByPagerComplaintUser(Pager pager,String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPagerComplaintUser"))
    .queryParam("pager",pager)
    .queryParam("userId",userId)
;  List<Complaint> aux = restTemplate.getForObject(builder.toUriString(), List<Complaint>.class);

 return aux;
}


public int countName(Complaint complaint,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countName"))
    .queryParam("complaint",complaint)
    .queryParam("user",user)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Complaint> queryByPagerName(Pager pager,Complaint complaint){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPagerName"))
    .queryParam("pager",pager)
    .queryParam("complaint",complaint)
;  List<Complaint> aux = restTemplate.getForObject(builder.toUriString(), List<Complaint>.class);

 return aux;
}


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}