package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.MemberLikeBoardService;
public class MemberLikeBoardServiceImpl implements MemberLikeBoardService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<MemberLikeBoard> likeBoard(long memberId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/likeBoard"))
    .queryParam("memberId",memberId)
;  List<MemberLikeBoard> aux = restTemplate.getForObject(builder.toUriString(), List<MemberLikeBoard>.class);

 return aux;
}


}