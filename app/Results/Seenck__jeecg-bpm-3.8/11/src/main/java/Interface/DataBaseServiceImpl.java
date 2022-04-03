package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.DataBaseService;
public class DataBaseServiceImpl implements DataBaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<CgformEnhanceJavaEntity> getCgformEnhanceJavaEntityByFormId(String formId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCgformEnhanceJavaEntityByFormId"))
    .queryParam("formId",formId)
;  List<CgformEnhanceJavaEntity> aux = restTemplate.getForObject(builder.toUriString(), List<CgformEnhanceJavaEntity>.class);

 return aux;
}


}