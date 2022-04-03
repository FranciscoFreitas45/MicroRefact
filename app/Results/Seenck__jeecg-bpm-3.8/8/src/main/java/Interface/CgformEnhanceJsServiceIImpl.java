package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgformEnhanceJsServiceI;
public class CgformEnhanceJsServiceIImpl implements CgformEnhanceJsServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType,String formId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCgformEnhanceJsByTypeFormId"))
    .queryParam("cgJsType",cgJsType)
    .queryParam("formId",formId)
;  CgformEnhanceJsEntity aux = restTemplate.getForObject(builder.toUriString(), CgformEnhanceJsEntity.class);

 return aux;
}


}