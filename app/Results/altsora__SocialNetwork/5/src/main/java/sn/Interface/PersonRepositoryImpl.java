package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.PersonRepository;
public class PersonRepositoryImpl implements PersonRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Person> findFriends(long id,int offset,int itemPerPage,String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findFriends"))
    .queryParam("id",id)
    .queryParam("offset",offset)
    .queryParam("itemPerPage",itemPerPage)
    .queryParam("name",name)
;  List<Person> aux = restTemplate.getForObject(builder.toUriString(), List<Person>.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Person> findRequests(long id,int offset,int itemPerPage,String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRequests"))
    .queryParam("id",id)
    .queryParam("offset",offset)
    .queryParam("itemPerPage",itemPerPage)
    .queryParam("name",name)
;  List<Person> aux = restTemplate.getForObject(builder.toUriString(), List<Person>.class);

 return aux;
}


public List<Person> findRecommendedFriends(long id,String city,Integer offset,int itemPerPage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRecommendedFriends"))
    .queryParam("id",id)
    .queryParam("city",city)
    .queryParam("offset",offset)
    .queryParam("itemPerPage",itemPerPage)
;  List<Person> aux = restTemplate.getForObject(builder.toUriString(), List<Person>.class);

 return aux;
}


}