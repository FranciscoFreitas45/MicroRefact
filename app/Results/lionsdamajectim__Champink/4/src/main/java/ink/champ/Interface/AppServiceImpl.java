package ink.champ.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import ink.champ.Interface.AppService;
public class AppServiceImpl implements AppService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void updateModel(User user,Model model,String page,String title){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateModel"))
    .queryParam("user",user)
    .queryParam("model",model)
    .queryParam("page",page)
    .queryParam("title",title)
;
  restTemplate.put(builder.toUriString(), null);
}


}