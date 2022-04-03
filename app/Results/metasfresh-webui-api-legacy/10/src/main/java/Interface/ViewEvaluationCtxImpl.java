package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewEvaluationCtxImpl implements ViewEvaluationCtx{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public ViewEvaluationCtx newInstanceFromCurrentContext(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstanceFromCurrentContext"))
  ViewEvaluationCtx aux = restTemplate.getForObject(builder.toUriString(), ViewEvaluationCtx.class);

 return aux;
}


}