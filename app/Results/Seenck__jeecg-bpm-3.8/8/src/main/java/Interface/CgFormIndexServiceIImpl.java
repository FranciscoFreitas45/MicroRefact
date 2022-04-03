package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgFormIndexServiceI;
public class CgFormIndexServiceIImpl implements CgFormIndexServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public boolean updateIndexes(CgFormHeadEntity cgFormHead){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateIndexes"))
    .queryParam("cgFormHead",cgFormHead)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void createIndexes(CgFormHeadEntity cgFormHead){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createIndexes"))
    .queryParam("cgFormHead",cgFormHead);

  restTemplate.put(builder.toUriString(), null);
}


}