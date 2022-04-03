package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BlackListRepository;
public class BlackListRepositoryImpl implements BlackListRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public BlackEntity findByUseridAndOrgi(String userid,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUseridAndOrgi"))
    .queryParam("userid",userid)
    .queryParam("orgi",orgi)
;  BlackEntity aux = restTemplate.getForObject(builder.toUriString(), BlackEntity.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<BlackEntity> findByEndtimeLessThan(Date endtime,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEndtimeLessThan"))
    .queryParam("endtime",endtime)
    .queryParam("page",page);
  Page<BlackEntity> aux = restTemplate.getForObject(builder.toUriString(), Page<BlackEntity>.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}