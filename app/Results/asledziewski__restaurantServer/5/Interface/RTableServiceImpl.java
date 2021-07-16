import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class RTableServiceImpl implements RTableService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Optional<RTable> getRTableById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRTableById"))
    .queryParam("id",id);
  Optional<RTable> aux = restTemplate.getForObject(builder.toUriString(), Optional<RTable>.class)

 return aux;
}


public List<RTable> getRTablesBySize(int size){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRTablesBySize"))
    .queryParam("size",size);
  List<RTable> aux = restTemplate.getForObject(builder.toUriString(), List<RTable>.class)

 return aux;
}


}