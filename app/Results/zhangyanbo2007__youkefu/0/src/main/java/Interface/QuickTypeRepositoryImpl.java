package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.QuickTypeRepository;
public class QuickTypeRepositoryImpl implements QuickTypeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<QuickType> findByOrgiAndQuicktype(String orgi,String quicktype){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndQuicktype"))
    .queryParam("orgi",orgi)
    .queryParam("quicktype",quicktype)
;  List<QuickType> aux = restTemplate.getForObject(builder.toUriString(), List<QuickType>.class);

 return aux;
}


public List<QuickType> findByOrgiAndQuicktypeAndCreater(String orgi,String quicktype,String creater){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndQuicktypeAndCreater"))
    .queryParam("orgi",orgi)
    .queryParam("quicktype",quicktype)
    .queryParam("creater",creater)
;  List<QuickType> aux = restTemplate.getForObject(builder.toUriString(), List<QuickType>.class);

 return aux;
}


public QuickType findByIdAndOrgi(String id,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
;  QuickType aux = restTemplate.getForObject(builder.toUriString(), QuickType.class);

 return aux;
}


public int countByOrgiAndNameAndParentid(String orgi,String name,String parentid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByOrgiAndNameAndParentid"))
    .queryParam("orgi",orgi)
    .queryParam("name",name)
    .queryParam("parentid",parentid)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}