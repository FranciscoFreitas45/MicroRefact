package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.FormulierService;
public class FormulierServiceImpl implements FormulierService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Iterable<Formulier> getAlleFormulierenVoorAdmin(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAlleFormulierenVoorAdmin"))
;  Iterable<Formulier> aux = restTemplate.getForObject(builder.toUriString(), Iterable<Formulier>.class);

 return aux;
}


public Formulier AdminStatusGoed(long formulierid,long medewerkerid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/AdminStatusGoed"))
    .queryParam("formulierid",formulierid)
    .queryParam("medewerkerid",medewerkerid)
;  Formulier aux = restTemplate.getForObject(builder.toUriString(), Formulier.class);

 return aux;
}


public Formulier AdminStatusFout(long id,long medewerkerid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/AdminStatusFout"))
    .queryParam("id",id)
    .queryParam("medewerkerid",medewerkerid)
;  Formulier aux = restTemplate.getForObject(builder.toUriString(), Formulier.class);

 return aux;
}


}