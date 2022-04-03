package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.SheetModel;
public class SheetModelImpl implements SheetModel{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public String content(Sheet sheet,String token,Model model){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/content"))
    .queryParam("sheet",sheet)
    .queryParam("token",token)
    .queryParam("model",model)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}