package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PricingConditionsViewFiltersImpl implements PricingConditionsViewFilters{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public Collection<DocumentFilterDescriptor> getFilterDescriptors(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilterDescriptors"))
  Collection<DocumentFilterDescriptor> aux = restTemplate.getForObject(builder.toUriString(), Collection<DocumentFilterDescriptor>.class);

 return aux;
}


public DocumentFilterDescriptorsProvider getFilterDescriptorsProvider(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilterDescriptorsProvider"))
  DocumentFilterDescriptorsProvider aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptorsProvider.class);

 return aux;
}


public DocumentFilterList extractFilters(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/extractFilters"))
    .queryParam("request",request);
  DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterList.class);

 return aux;
}


}