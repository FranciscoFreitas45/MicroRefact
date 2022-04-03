package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.ReplyService;
public class ReplyServiceImpl implements ReplyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Replylist> getRepliesByBoardId(long boardId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRepliesByBoardId"))
    .queryParam("boardId",boardId)
;  List<Replylist> aux = restTemplate.getForObject(builder.toUriString(), List<Replylist>.class);

 return aux;
}


public boolean writeReply(long boardId,long parent,String content,Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/writeReply"))
    .queryParam("boardId",boardId)
    .queryParam("parent",parent)
    .queryParam("content",content)
    .queryParam("member",member)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean deleteReply(long replyId,long parent,Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteReply"))
    .queryParam("replyId",replyId)
    .queryParam("parent",parent)
    .queryParam("member",member)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}