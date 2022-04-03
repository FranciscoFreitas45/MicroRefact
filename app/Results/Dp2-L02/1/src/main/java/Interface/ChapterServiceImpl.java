package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ChapterService;
public class ChapterServiceImpl implements ChapterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Chapter> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Chapter> aux = restTemplate.getForObject(builder.toUriString(), List<Chapter>.class);

 return aux;
}


public Chapter findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


public Chapter loggedChapter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedChapter"))
  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


public Chapter reconstructPersonalData(Chapter chapter,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructPersonalData"))
    .queryParam("chapter",chapter)
    .queryParam("binding",binding);
  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


public Chapter update(Chapter chapter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("chapter",chapter);
  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


}