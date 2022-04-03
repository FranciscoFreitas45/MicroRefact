package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class CompositeDefaultViewProfileIdProviderImpl implements CompositeDefaultViewProfileIdProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public CompositeDefaultViewProfileIdProvider of(List<DefaultViewProfileIdProvider> providers){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("providers",providers);
  CompositeDefaultViewProfileIdProvider aux = restTemplate.getForObject(builder.toUriString(), CompositeDefaultViewProfileIdProvider.class);

 return aux;
}


public void setDefaultProfileIdOverride(WindowId windowId,ViewProfileId profileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDefaultProfileIdOverride"))
    .queryParam("windowId",windowId)
    .queryParam("profileId",profileId);

  restTemplate.put(builder.toUriString(), null);
}


public ViewProfileId getDefaultProfileIdByWindowId(WindowId windowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDefaultProfileIdByWindowId"))
    .queryParam("windowId",windowId);
  ViewProfileId aux = restTemplate.getForObject(builder.toUriString(), ViewProfileId.class);

 return aux;
}


}