package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BrotherhoodService;
public class BrotherhoodServiceImpl implements BrotherhoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Brotherhood save(Brotherhood h){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("h",h)
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood securityAndBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/securityAndBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public Brotherhood loggedBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedBrotherhood"))
;  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public void loggedAsBrotherhood(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsBrotherhood"))
;
  restTemplate.put(builder.toUriString(), null);
}


public Brotherhood findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  Brotherhood aux = restTemplate.getForObject(builder.toUriString(), Brotherhood.class);

 return aux;
}


public List<Member> getMembersOfBrotherhood(Brotherhood bro){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMembersOfBrotherhood"))
    .queryParam("bro",bro);
  List<Member> aux = restTemplate.getForObject(builder.toUriString(), List<Member>.class);

 return aux;
}


public List<String> getPositions(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPositions"))
  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public Enrolment getEnrolment(Member m){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnrolment"))
    .queryParam("m",m);
  Enrolment aux = restTemplate.getForObject(builder.toUriString(), Enrolment.class);

 return aux;
}


public List<Enrolment> getPengingEnrolments(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPengingEnrolments"))
  List<Enrolment> aux = restTemplate.getForObject(builder.toUriString(), List<Enrolment>.class);

 return aux;
}


}