package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ParadeService;
public class ParadeServiceImpl implements ParadeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Parade findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  Parade aux = restTemplate.getForObject(builder.toUriString(), Parade.class);

 return aux;
}


public Parade save(Parade parade){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("parade",parade)
;  Parade aux = restTemplate.getForObject(builder.toUriString(), Parade.class);

 return aux;
}


public List<Parade> getParadesByArea(Area area){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParadesByArea"))
    .queryParam("area",area)
;  List<Parade> aux = restTemplate.getForObject(builder.toUriString(), List<Parade>.class);

 return aux;
}


public Boolean hasArea(Chapter chapter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasArea"))
    .queryParam("chapter",chapter);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public List<Parade> filterParadesChapter(Chapter chapter,String option){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filterParadesChapter"))
    .queryParam("chapter",chapter)
    .queryParam("option",option);
  List<Parade> aux = restTemplate.getForObject(builder.toUriString(), List<Parade>.class);

 return aux;
}


public Parade reconstrucParadeStatus(Parade parade){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstrucParadeStatus"))
    .queryParam("parade",parade);
  Parade aux = restTemplate.getForObject(builder.toUriString(), Parade.class);

 return aux;
}


}