package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.ThemeSettingService;
public class ThemeSettingServiceImpl implements ThemeSettingService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public Map<String,Object> listAsMapBy(String themeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAsMapBy"))
    .queryParam("themeId",themeId)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}