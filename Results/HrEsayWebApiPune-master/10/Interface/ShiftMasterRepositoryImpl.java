import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ShiftMasterRepositoryImpl implements ShiftMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<ShiftMaster> findByStatus(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStatus"))
    .queryParam("i",i);
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


}