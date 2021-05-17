import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsMaterielRepositoryImpl implements QmsMaterielRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsMateriel> findByIdAndFlagStatus(Long valueOf,String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIdAndFlagStatus"))
    .queryParam("valueOf",valueOf)
    .queryParam("string",string);
  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class)

 return aux;
}


public List<QmsMateriel> findByMaterielCdAndFlagStatus(String materielCd,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielCdAndFlagStatus"))
    .queryParam("materielCd",materielCd)
    .queryParam("flagStatus",flagStatus);
  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class)

 return aux;
}


}