import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class InscripcionRepImpl implements InscripcionRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public void saveI(int fk_id_grupo,int fk_id_profesor,String calif,boolean aprobado){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("saveI"))
    .queryParam("fk_id_grupo",fk_id_grupo)
    .queryParam("fk_id_profesor",fk_id_profesor)
    .queryParam("calif",calif)
    .queryParam("aprobado",aprobado);

  restTemplate.put(builder.toUriString(), null)



}