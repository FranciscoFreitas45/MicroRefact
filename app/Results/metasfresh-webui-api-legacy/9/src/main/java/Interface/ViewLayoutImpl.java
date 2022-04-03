package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewLayoutImpl implements ViewLayout{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<DocumentLayoutElementDescriptor.Builder> getElements(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getElements"))
  List<DocumentLayoutElementDescriptor.Builder> aux = restTemplate.getForObject(builder.toUriString(), List<DocumentLayoutElementDescriptor.Builder>.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getFields(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFields"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getWidgetType(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWidgetType"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object size(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/size"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getCaption(String adLanguage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCaption"))
    .queryParam("adLanguage",adLanguage);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}