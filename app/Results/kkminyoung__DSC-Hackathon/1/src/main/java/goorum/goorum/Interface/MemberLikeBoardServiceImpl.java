package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.MemberLikeBoardService;
public class MemberLikeBoardServiceImpl implements MemberLikeBoardService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public boolean isLike(long boardId,long memberId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLike"))
    .queryParam("boardId",boardId)
    .queryParam("memberId",memberId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void dislike(long memberId,long boardId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/dislike"))
    .queryParam("memberId",memberId)
    .queryParam("boardId",boardId)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean like(long memberId,long boardId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/like"))
    .queryParam("memberId",memberId)
    .queryParam("boardId",boardId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}