package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JeecgMinidaoDao;
public class JeecgMinidaoDaoImpl implements JeecgMinidaoDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Map<String,String>> getProCity(String pid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProCity"))
    .queryParam("pid",pid)
;  List<Map<String,String>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,String>>.class);

 return aux;
}


public List<Map<String,String>> getAllRegions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllRegions"))
;  List<Map<String,String>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,String>>.class);

 return aux;
}


}