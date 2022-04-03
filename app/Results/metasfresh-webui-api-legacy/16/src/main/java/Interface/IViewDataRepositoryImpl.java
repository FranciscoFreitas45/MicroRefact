package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewDataRepositoryImpl implements IViewDataRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewFilterDescriptors"))
  DocumentFilterDescriptorsProvider aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptorsProvider.class);

 return aux;
}


}