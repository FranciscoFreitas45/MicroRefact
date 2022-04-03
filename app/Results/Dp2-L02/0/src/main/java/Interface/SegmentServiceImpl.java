package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SegmentService;
public class SegmentServiceImpl implements SegmentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Segment createSegment(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createSegment"))
;  Segment aux = restTemplate.getForObject(builder.toUriString(), Segment.class);

 return aux;
}


}