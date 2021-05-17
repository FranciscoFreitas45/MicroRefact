import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsProductionInspectionRepositoryImpl implements QmsProductionInspectionRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(Integer s,String m){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielIdAndSerialNumber"))
    .queryParam("s",s)
    .queryParam("m",m);
  List<QmsProductionInspection> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProductionInspection>.class)

 return aux;
}


}