package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.NoteDao;
public class NoteDaoImpl implements NoteDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Noteuser finduserid(long noteid,Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/finduserid"))
    .queryParam("noteid",noteid)
    .queryParam("userId",userId)
;  Noteuser aux = restTemplate.getForObject(builder.toUriString(), Noteuser.class);

 return aux;
}


public List<Note> findByCatalogId(long catalogId,long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCatalogId"))
    .queryParam("catalogId",catalogId)
    .queryParam("userid",userid)
;  List<Note> aux = restTemplate.getForObject(builder.toUriString(), List<Note>.class);

 return aux;
}


}