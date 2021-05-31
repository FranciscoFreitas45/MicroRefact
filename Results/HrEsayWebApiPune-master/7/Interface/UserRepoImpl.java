import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserRepoImpl implements UserRepo{

 private RestTemplate restTemplate;

  String url = "http://21";


public int updateAccLoc(List<Integer> empIdList,String loc){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAccLoc"))
    .queryParam("empIdList",empIdList)
    .queryParam("loc",loc);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int updateUserLoginType(List<Integer> empIdList,String loginType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUserLoginType"))
    .queryParam("empIdList",empIdList)
    .queryParam("loginType",loginType);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}