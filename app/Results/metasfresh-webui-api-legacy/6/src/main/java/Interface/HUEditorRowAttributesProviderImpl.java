package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUEditorRowAttributesProviderImpl implements HUEditorRowAttributesProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public void invalidateAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateAll"))

  restTemplate.put(builder.toUriString(), null);
}


public DocumentId createAttributeKey(HuId huId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createAttributeKey"))
    .queryParam("huId",huId);
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public HUEditorRowAttributes getAttributes(DocumentId viewRowId,DocumentId huId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAttributes"))
    .queryParam("viewRowId",viewRowId)
    .queryParam("huId",huId);
  HUEditorRowAttributes aux = restTemplate.getForObject(builder.toUriString(), HUEditorRowAttributes.class);

 return aux;
}


}