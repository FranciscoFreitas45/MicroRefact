import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsProductionInspectionRepositoryImpl implements QmsProductionInspectionRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByBomTechnologyIdAndFlagStatus"))
    .queryParam("BomTechnologyId",BomTechnologyId)
    .queryParam("FlagStatus",FlagStatus);
  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class)

 return aux;
}


public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByBomTechnologyIdAndFlagStatus"))
    .queryParam("BomTechnologyId",BomTechnologyId)
    .queryParam("FlagStatus",FlagStatus);
  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class)

 return aux;
}


public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByBomTechnologyIdAndFlagStatus"))
    .queryParam("BomTechnologyId",BomTechnologyId)
    .queryParam("FlagStatus",FlagStatus);
  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class)

 return aux;
}


}