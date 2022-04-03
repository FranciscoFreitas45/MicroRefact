package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MutiLangServiceI;
public class MutiLangServiceIImpl implements MutiLangServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public String getLang(String lang_key,String args){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLang"))
    .queryParam("lang_key",lang_key)
    .queryParam("args",args)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}