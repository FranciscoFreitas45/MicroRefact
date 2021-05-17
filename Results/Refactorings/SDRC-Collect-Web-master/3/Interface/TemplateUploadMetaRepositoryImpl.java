import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class TemplateUploadMetaRepositoryImpl implements TemplateUploadMetaRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public TemplateUploadMeta save(TemplateUploadMeta templateUploadMeta){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("save"))
    .queryParam("templateUploadMeta",templateUploadMeta);
  TemplateUploadMeta aux = restTemplate.getForObject(builder.toUriString(), TemplateUploadMeta.class)

 return aux;
}


}