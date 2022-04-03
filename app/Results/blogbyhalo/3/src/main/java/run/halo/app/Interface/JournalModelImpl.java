package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.JournalModel;
public class JournalModelImpl implements JournalModel{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String list(Integer page,Model model){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("page",page)
    .queryParam("model",model)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}