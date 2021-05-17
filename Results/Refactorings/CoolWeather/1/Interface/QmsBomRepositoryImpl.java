import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsBomRepositoryImpl implements QmsBomRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsBom> findByVehicleIdAndFlagStatus(Integer vehicleType,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByVehicleIdAndFlagStatus"))
    .queryParam("vehicleType",vehicleType)
    .queryParam("flagStatus",flagStatus);
  List<QmsBom> aux = restTemplate.getForObject(builder.toUriString(), List<QmsBom>.class)

 return aux;
}


}