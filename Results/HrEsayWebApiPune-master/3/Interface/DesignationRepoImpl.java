import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DesignationRepoImpl implements DesignationRepo{

 private RestTemplate restTemplate;

  String url = "http://9";


public List<Designation> findByNameAndCompanyIdAndDesigIdNot(String trim,int compId,int primaryKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByNameAndCompanyIdAndDesigIdNot"))
    .queryParam("trim",trim)
    .queryParam("compId",compId)
    .queryParam("primaryKey",primaryKey);
  List<Designation> aux = restTemplate.getForObject(builder.toUriString(), List<Designation>.class)

 return aux;
}


public List<Designation> findByNameAndCompanyId(String desgn,int compId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByNameAndCompanyId"))
    .queryParam("desgn",desgn)
    .queryParam("compId",compId);
  List<Designation> aux = restTemplate.getForObject(builder.toUriString(), List<Designation>.class)

 return aux;
}


}