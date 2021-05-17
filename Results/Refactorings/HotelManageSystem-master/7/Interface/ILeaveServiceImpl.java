import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ILeaveServiceImpl implements ILeaveService{

 private RestTemplate restTemplate;

  String url = "http://8";


public float findTotalLeaveTimes(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTotalLeaveTimes"))
    .queryParam("userName",userName);
  float aux = restTemplate.getForObject(builder.toUriString(), float.class)

 return aux;
}


public int findTatalPersonLeave(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTatalPersonLeave"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<Map<Object,Object>> findByyearAndOntudytimeleave(Integer year,String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByyearAndOntudytimeleave"))
    .queryParam("year",year)
    .queryParam("userName",userName);
  List<Map<Object,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<Object,Object>>.class)

 return aux;
}


public List<Map<Object,Object>> findleave(Integer year){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findleave"))
    .queryParam("year",year);
  List<Map<Object,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<Object,Object>>.class)

 return aux;
}


}