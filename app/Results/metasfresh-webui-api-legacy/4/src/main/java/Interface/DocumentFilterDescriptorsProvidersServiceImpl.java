package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentFilterDescriptorsProvidersServiceImpl implements DocumentFilterDescriptorsProvidersService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public DocumentFilterDescriptorsProvider createFiltersProvider(AdTabId adTabId,String tableName,Collection<DocumentFieldDescriptor> fields){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createFiltersProvider"))
    .queryParam("adTabId",adTabId)
    .queryParam("tableName",tableName)
    .queryParam("fields",fields);
  DocumentFilterDescriptorsProvider aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptorsProvider.class);

 return aux;
}


}