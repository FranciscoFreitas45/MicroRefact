import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AdvanceRepoImpl implements AdvanceRepo{

 private RestTemplate restTemplate;

  String url = "http://21";


public int deleteAdvanceBydefault(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAdvanceBydefault"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public int updateAdv(int empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAdv"))
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}