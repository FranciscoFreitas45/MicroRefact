package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MetadataRepository;
public class MetadataRepositoryImpl implements MetadataRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public MetadataTable findByTablename(String tablename){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTablename"))
    .queryParam("tablename",tablename)
;  MetadataTable aux = restTemplate.getForObject(builder.toUriString(), MetadataTable.class);

 return aux;
}


}