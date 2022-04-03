package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.AlbumDAO;
public class AlbumDAOImpl implements AlbumDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public List<Album> getEnabledByFilter(String filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnabledByFilter"))
    .queryParam("filter",filter)
;  List<Album> aux = restTemplate.getForObject(builder.toUriString(), List<Album>.class);

 return aux;
}


}