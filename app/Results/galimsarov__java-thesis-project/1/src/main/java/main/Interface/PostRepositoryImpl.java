package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.PostRepository;
public class PostRepositoryImpl implements PostRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object getOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<String> getPostsForTheYear(String year){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPostsForTheYear"))
    .queryParam("year",year)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public int getPostsCountOfUser(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPostsCountOfUser"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getLikesCountOfUsersPosts(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLikesCountOfUsersPosts"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getDisLikesCountOfUsersPosts(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisLikesCountOfUsersPosts"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getViewsCountOfUsersPosts(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewsCountOfUsersPosts"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Date getFirstPostOfUser(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFirstPostOfUser"))
    .queryParam("userId",userId)
;  Date aux = restTemplate.getForObject(builder.toUriString(), Date.class);

 return aux;
}


public Object getTime(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTime"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int getPostsCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPostsCount"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getLikesCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLikesCount"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getDisLikesCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisLikesCount"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getViewsCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewsCount"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Date getFirstPost(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFirstPost"))
;  Date aux = restTemplate.getForObject(builder.toUriString(), Date.class);

 return aux;
}


}