package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.Interface.BotigaRepository;
public class BotigaRepositoryImpl implements BotigaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Botiga findById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Botiga aux = restTemplate.getForObject(builder.toUriString(), Botiga.class);

 return aux;
}


public List<Botiga> findByOrganitzacioId(Integer organitzacioId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrganitzacioId"))
    .queryParam("organitzacioId",organitzacioId)
;  List<Botiga> aux = restTemplate.getForObject(builder.toUriString(), List<Botiga>.class);

 return aux;
}


}