package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.AuthenticationFailureHandler;
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public void onFailure(HttpServletRequest request,HttpServletResponse response,AbstractHaloException exception){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/onFailure"))
    .queryParam("request",request)
    .queryParam("response",response)
    .queryParam("exception",exception)
;
  restTemplate.put(builder.toUriString(), null);
}


}