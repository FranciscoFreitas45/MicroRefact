package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class MaterialCockpitFiltersImpl implements MaterialCockpitFilters{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilterDescriptors"))
  DocumentFilterDescriptorsProvider aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptorsProvider.class);

 return aux;
}


public DocumentFilterList extractDocumentFilters(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/extractDocumentFilters"))
    .queryParam("request",request);
  DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterList.class);

 return aux;
}


public DocumentFilterList createAutoFilters(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createAutoFilters"))
  DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterList.class);

 return aux;
}


public LocalDate getFilterByDate(DocumentFilterList filters){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilterByDate"))
    .queryParam("filters",filters);
  LocalDate aux = restTemplate.getForObject(builder.toUriString(), LocalDate.class);

 return aux;
}


public IQuery<I_MD_Cockpit> createQuery(DocumentFilterList filters){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createQuery"))
    .queryParam("filters",filters);
  IQuery<I_MD_Cockpit> aux = restTemplate.getForObject(builder.toUriString(), IQuery<I_MD_Cockpit>.class);

 return aux;
}


public Object list(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}