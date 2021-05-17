import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtSubgroupValsEnRepositoryImpl implements UtSubgroupValsEnRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Object[]> fetchIndicatorAndUnitBySectorNId(Integer SectorNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("fetchIndicatorAndUnitBySectorNId"))
    .queryParam("SectorNid",SectorNid);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer IndicatorNid,Integer UnitNId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("fetchSubgroupByIndicatorAndUnit"))
    .queryParam("IndicatorNid",IndicatorNid)
    .queryParam("UnitNId",UnitNId);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


}