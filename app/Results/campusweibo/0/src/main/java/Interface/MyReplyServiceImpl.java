package Interface;
 import DTO.MyReply;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

 import java.util.List;

public class MyReplyServiceImpl implements MyReplyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<MyReply> getAllReply(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllReply"))
    .queryParam("id",id);
  List<MyReply> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}