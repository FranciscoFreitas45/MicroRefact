import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsMaterielEntryRepositoryImpl implements QmsMaterielEntryRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(Integer materielId,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByMaterielIdAndFlagStatus"))
    .queryParam("materielId",materielId)
    .queryParam("flagStatus",flagStatus);
  List<QmsMaterielEntry> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielEntry>.class)

 return aux;
}


}