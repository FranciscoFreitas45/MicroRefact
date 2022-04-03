package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ClientService;
public class ClientServiceImpl implements ClientService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<ClientContact> findClientContacts(Page<ClientContact> page,String clientId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findClientContacts"))
    .queryParam("page",page)
    .queryParam("clientId",clientId)
;  Page<ClientContact> aux = restTemplate.getForObject(builder.toUriString(), Page<ClientContact>.class);

 return aux;
}


public Object unique(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/unique"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object uniqueEntity(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uniqueEntity"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void delThemCascade(String string,List<String> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delThemCascade"))
    .queryParam("string",string)
    .queryParam("ids",ids)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object add(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void update(ClientContact c){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("c",c)
;
  restTemplate.put(builder.toUriString(), null);
}


public void save(String clientId,ClientContact data){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("clientId",clientId)
    .queryParam("data",data)
;
  restTemplate.put(builder.toUriString(), null);
}


}