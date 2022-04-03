package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.WorkflowDeployService;
public class WorkflowDeployServiceImpl implements WorkflowDeployService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Deployment deployModelWithStatus(Model modelData,ObjectNode modelNode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deployModelWithStatus"))
    .queryParam("modelData",modelData)
    .queryParam("modelNode",modelNode)
;  Deployment aux = restTemplate.getForObject(builder.toUriString(), Deployment.class);

 return aux;
}


}