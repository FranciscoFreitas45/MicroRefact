package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OptionService;
public class OptionServiceImpl implements OptionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Boolean isEnabledAbsolutePath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnabledAbsolutePath"))
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public String getBlogBaseUrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBlogBaseUrl"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getLinksPrefix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLinksPrefix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getPhotosPrefix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPhotosPrefix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getJournalsPrefix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getJournalsPrefix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public SheetPermalinkType getSheetPermalinkType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSheetPermalinkType"))
;  SheetPermalinkType aux = restTemplate.getForObject(builder.toUriString(), SheetPermalinkType.class);

 return aux;
}


public String getPathSuffix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPathSuffix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getSheetPrefix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSheetPrefix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}