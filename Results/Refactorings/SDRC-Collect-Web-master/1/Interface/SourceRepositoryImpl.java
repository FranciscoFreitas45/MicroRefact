import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SourceRepositoryImpl implements SourceRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<UtIndicatorClassificationsEn> findByIUS_Nid(Integer iusNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIUS_Nid"))
    .queryParam("iusNid",iusNid);
  List<UtIndicatorClassificationsEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtIndicatorClassificationsEn>.class)

 return aux;
}


public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(Integer iusNid,Integer levelNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIUSandLevel_Nid"))
    .queryParam("iusNid",iusNid)
    .queryParam("levelNid",levelNid);
  List<UtIndicatorClassificationsEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtIndicatorClassificationsEn>.class)

 return aux;
}


}