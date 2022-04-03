package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SegmentService;
public class SegmentServiceImpl implements SegmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Segment> getSegmentByParade(Integer paradeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSegmentByParade"))
    .queryParam("paradeId",paradeId)
;  List<Segment> aux = restTemplate.getForObject(builder.toUriString(), List<Segment>.class);

 return aux;
}


}