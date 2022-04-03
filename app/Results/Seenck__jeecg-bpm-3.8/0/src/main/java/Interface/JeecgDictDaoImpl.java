package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JeecgDictDao;
public class JeecgDictDaoImpl implements JeecgDictDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<DictEntity> queryCustomDict(String dicTable,String dicCode,String dicText){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCustomDict"))
    .queryParam("dicTable",dicTable)
    .queryParam("dicCode",dicCode)
    .queryParam("dicText",dicText)
;  List<DictEntity> aux = restTemplate.getForObject(builder.toUriString(), List<DictEntity>.class);

 return aux;
}


public List<DictEntity> querySystemDict(String dicCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/querySystemDict"))
    .queryParam("dicCode",dicCode)
;  List<DictEntity> aux = restTemplate.getForObject(builder.toUriString(), List<DictEntity>.class);

 return aux;
}


}