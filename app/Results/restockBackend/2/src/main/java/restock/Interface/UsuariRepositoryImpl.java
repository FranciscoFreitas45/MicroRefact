package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.DTO.Producte;
import restock.Interface.UsuariRepository;
public class UsuariRepositoryImpl implements UsuariRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Usuari findByUserAndOrganitzacioId(String user,Integer organitzacioId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserAndOrganitzacioId"))
    .queryParam("user",user)
    .queryParam("organitzacioId",organitzacioId);
  Usuari aux = restTemplate.getForObject(builder.toUriString(), Usuari.class);

 return aux;
}


public Usuari findById(Integer usuariId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("usuariId",usuariId);
  Usuari aux = restTemplate.getForObject(builder.toUriString(), Usuari.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}