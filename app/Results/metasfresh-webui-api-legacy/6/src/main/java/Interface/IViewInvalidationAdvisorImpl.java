package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewInvalidationAdvisorImpl implements IViewInvalidationAdvisor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Set<DocumentId> findAffectedRowIds(TableRecordReferenceSet recordRefs,IView view){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAffectedRowIds"))
    .queryParam("recordRefs",recordRefs)
    .queryParam("view",view);
  Set<DocumentId> aux = restTemplate.getForObject(builder.toUriString(), Set<DocumentId>.class);

 return aux;
}


}