import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveStructureDetailsRepoImpl implements LeaveStructureDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<LeaveStructureDetails> findByLvsIdAndDelStatus(int lvsId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLvsIdAndDelStatus"))
    .queryParam("lvsId",lvsId)
    .queryParam("i",i);
  List<LeaveStructureDetails> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveStructureDetails>.class)

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}