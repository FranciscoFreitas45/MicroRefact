package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProcessRestControllerImpl implements ProcessRestController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(WebuiPreconditionsContext preconditionsContext){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/streamDocumentRelatedProcesses"))
    .queryParam("preconditionsContext",preconditionsContext);
  Stream<WebuiRelatedProcessDescriptor> aux = restTemplate.getForObject(builder.toUriString(), Stream<WebuiRelatedProcessDescriptor>.class);

 return aux;
}


public Object filter(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filter"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}