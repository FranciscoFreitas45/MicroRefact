package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.UtDataRepository;
public class UtDataRepositoryImpl implements UtDataRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public UtAreaEn[] getAreaNid(String areaId,Integer childLevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAreaNid"))
    .queryParam("areaId",areaId)
    .queryParam("childLevel",childLevel)
;  UtAreaEn[] aux = restTemplate.getForObject(builder.toUriString(), UtAreaEn[].class);

 return aux;
}


public List<Object[]> findDataForCompositeIndex(Integer iusNId,Integer timePeriodNid,Integer[] areaNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDataForCompositeIndex"))
    .queryParam("iusNId",iusNId)
    .queryParam("timePeriodNid",timePeriodNid)
    .queryParam("areaNid",areaNid)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


}