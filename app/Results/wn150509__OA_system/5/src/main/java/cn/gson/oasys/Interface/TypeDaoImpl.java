package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.TypeDao;
public class TypeDaoImpl implements TypeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public SystemTypeList findByTypeModelAndTypeName(String typeModel,String typeName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTypeModelAndTypeName"))
    .queryParam("typeModel",typeModel)
    .queryParam("typeName",typeName)
;  SystemTypeList aux = restTemplate.getForObject(builder.toUriString(), SystemTypeList.class);

 return aux;
}


public List<SystemTypeList> findByTypeModel(String typeModel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTypeModel"))
    .queryParam("typeModel",typeModel)
;  List<SystemTypeList> aux = restTemplate.getForObject(builder.toUriString(), List<SystemTypeList>.class);

 return aux;
}


}