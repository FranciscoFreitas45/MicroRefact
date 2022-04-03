package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.RolepowerlistDao;
public class RolepowerlistDaoImpl implements RolepowerlistDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Rolemenu> findname(Long id,Long roleid,Boolean bo,Boolean le,String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findname"))
    .queryParam("id",id)
    .queryParam("roleid",roleid)
    .queryParam("bo",bo)
    .queryParam("le",le)
    .queryParam("name",name)
;  List<Rolemenu> aux = restTemplate.getForObject(builder.toUriString(), List<Rolemenu>.class);

 return aux;
}


public List<Rolemenu> findbyparentxianall(Long id,Long roleid,Boolean bo,Boolean le){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findbyparentxianall"))
    .queryParam("id",id)
    .queryParam("roleid",roleid)
    .queryParam("bo",bo)
    .queryParam("le",le)
;  List<Rolemenu> aux = restTemplate.getForObject(builder.toUriString(), List<Rolemenu>.class);

 return aux;
}


}