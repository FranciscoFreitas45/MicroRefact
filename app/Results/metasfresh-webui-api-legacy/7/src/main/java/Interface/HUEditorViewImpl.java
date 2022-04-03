package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUEditorViewImpl implements HUEditorView{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public boolean matchesAnyRowRecursive(HUEditorRowFilter filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/matchesAnyRowRecursive"))
    .queryParam("filter",filter);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/streamAllRecursive"))
    .queryParam("filter",filter);
  Stream<HUEditorRow> aux = restTemplate.getForObject(builder.toUriString(), Stream<HUEditorRow>.class);

 return aux;
}


}