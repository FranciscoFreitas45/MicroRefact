package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TSDictTableConfigServiceI;
public class TSDictTableConfigServiceIImpl implements TSDictTableConfigServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public boolean checkDictAuth(String dictionary,String dictCondition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkDictAuth"))
    .queryParam("dictionary",dictionary)
    .queryParam("dictCondition",dictCondition)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Object getDictText(String dictionary,String dictCondition,String value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDictText"))
    .queryParam("dictionary",dictionary)
    .queryParam("dictCondition",dictCondition)
    .queryParam("value",value)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}