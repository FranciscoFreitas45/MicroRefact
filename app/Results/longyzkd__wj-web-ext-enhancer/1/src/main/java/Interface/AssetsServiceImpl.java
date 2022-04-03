package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AssetsService;
public class AssetsServiceImpl implements AssetsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object list(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
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


public void delCascade(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delCascade"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object addObj(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addObj"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void updateForm(AssetsType data){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateForm"))
    .queryParam("data",data)
;
  restTemplate.put(builder.toUriString(), null);
}


}