package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgformButtonServiceI;
public class CgformButtonServiceIImpl implements CgformButtonServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public List<CgformButtonEntity> getCgformButtonListByFormId(String formId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCgformButtonListByFormId"))
    .queryParam("formId",formId)
;  List<CgformButtonEntity> aux = restTemplate.getForObject(builder.toUriString(), List<CgformButtonEntity>.class);

 return aux;
}


}