import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AllowancesRepoImpl implements AllowancesRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<Allowances> findBydelStatusAndIsActive(int del,int active){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBydelStatusAndIsActive"))
    .queryParam("del",del)
    .queryParam("active",active);
  List<Allowances> aux = restTemplate.getForObject(builder.toUriString(), List<Allowances>.class)

 return aux;
}


}