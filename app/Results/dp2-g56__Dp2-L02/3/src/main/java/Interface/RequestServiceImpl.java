package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.RequestService;
public class RequestServiceImpl implements RequestService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void delete(Request request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("request",request)
;
  restTemplate.put(builder.toUriString(), null);
}


public Collection<Request> getRequestsByMember(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRequestsByMember"))
    .queryParam("member",member);
  Collection<Request> aux = restTemplate.getForObject(builder.toUriString(), Collection<Request>.class);

 return aux;
}


public Collection<Request> getRequestsByMemberAndStatus(Member member,Status status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRequestsByMemberAndStatus"))
    .queryParam("member",member)
    .queryParam("status",status);
  Collection<Request> aux = restTemplate.getForObject(builder.toUriString(), Collection<Request>.class);

 return aux;
}


public void deleteRequestAsMember(Member member,int requestId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteRequestAsMember"))
    .queryParam("member",member)
    .queryParam("requestId",requestId);

  restTemplate.put(builder.toUriString(), null);
}


public boolean canRequest(Member member,int paradeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/canRequest"))
    .queryParam("member",member)
    .queryParam("paradeId",paradeId);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void createRequestAsMember(Member member,int paradeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createRequestAsMember"))
    .queryParam("member",member)
    .queryParam("paradeId",paradeId);

  restTemplate.put(builder.toUriString(), null);
}


}