package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.CommentBlackListService;
public class CommentBlackListServiceImpl implements CommentBlackListService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public CommentViolationTypeEnum commentsBanStatus(String ipAddress){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/commentsBanStatus"))
    .queryParam("ipAddress",ipAddress)
;  CommentViolationTypeEnum aux = restTemplate.getForObject(builder.toUriString(), CommentViolationTypeEnum.class);

 return aux;
}


}