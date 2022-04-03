package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ChapterService;
public class ChapterServiceImpl implements ChapterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Area> listFreeAreas(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listFreeAreas"))
;  List<Area> aux = restTemplate.getForObject(builder.toUriString(), List<Area>.class);

 return aux;
}


public Chapter createChapter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createChapter"))
;  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


public Chapter reconstruct(FormObjectChapter formObjectChapter,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("formObjectChapter",formObjectChapter)
    .queryParam("binding",binding)
;  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


public Chapter saveCreate(Chapter chapter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCreate"))
    .queryParam("chapter",chapter)
;  Chapter aux = restTemplate.getForObject(builder.toUriString(), Chapter.class);

 return aux;
}


}