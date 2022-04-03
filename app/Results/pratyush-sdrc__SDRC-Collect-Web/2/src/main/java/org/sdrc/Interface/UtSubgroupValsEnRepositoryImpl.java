package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.UtSubgroupValsEnRepository;
public class UtSubgroupValsEnRepositoryImpl implements UtSubgroupValsEnRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://24";


public List<Object[]> fetchIndicatorAndUnitBySectorNId(Integer SectorNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchIndicatorAndUnitBySectorNId"))
    .queryParam("SectorNid",SectorNid)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer IndicatorNid,Integer UnitNId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchSubgroupByIndicatorAndUnit"))
    .queryParam("IndicatorNid",IndicatorNid)
    .queryParam("UnitNId",UnitNId)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


}