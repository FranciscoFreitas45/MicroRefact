package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsPartsAssemblyRelationRepository;
public class QmsPartsAssemblyRelationRepositoryImpl implements QmsPartsAssemblyRelationRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://18";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public QmsPartsAssemblyRelation findByIdAndFlagStatus(Long id,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndFlagStatus"))
    .queryParam("id",id)
    .queryParam("string",string)
;  QmsPartsAssemblyRelation aux = restTemplate.getForObject(builder.toUriString(), QmsPartsAssemblyRelation.class);

 return aux;
}


public List<QmsPartsAssemblyRelation> findAllByFlagStatusAndBomTechnologyId(String FlagStatus,Integer bomTechnologyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByFlagStatusAndBomTechnologyId"))
    .queryParam("FlagStatus",FlagStatus)
    .queryParam("bomTechnologyId",bomTechnologyId)
;  List<QmsPartsAssemblyRelation> aux = restTemplate.getForObject(builder.toUriString(), List<QmsPartsAssemblyRelation>.class);

 return aux;
}


}