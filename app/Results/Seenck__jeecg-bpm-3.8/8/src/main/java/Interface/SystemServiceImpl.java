package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SystemService;
public class SystemServiceImpl implements SystemService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void addLog(String LogContent,Short operatetype,Short loglevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLog"))
    .queryParam("LogContent",LogContent)
    .queryParam("operatetype",operatetype)
    .queryParam("loglevel",loglevel)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object getEntity(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEntity"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<DictEntity> queryDict(String dicTable,String dicCode,String dicText){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryDict"))
    .queryParam("dicTable",dicTable)
    .queryParam("dicCode",dicCode)
    .queryParam("dicText",dicText)
;  List<DictEntity> aux = restTemplate.getForObject(builder.toUriString(), List<DictEntity>.class);

 return aux;
}


public Object getCountForJdbcParam(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountForJdbcParam"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getSession(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSession"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object executeSql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/executeSql"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProperty"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findHql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHql"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object batchSave(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/batchSave"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}