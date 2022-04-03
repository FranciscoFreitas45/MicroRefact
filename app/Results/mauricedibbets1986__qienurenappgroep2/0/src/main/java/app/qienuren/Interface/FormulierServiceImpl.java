package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.FormulierService;
public class FormulierServiceImpl implements FormulierService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Formulier updateFormulier(Formulier nieuwF){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateFormulier"))
    .queryParam("nieuwF",nieuwF)
;  Formulier aux = restTemplate.getForObject(builder.toUriString(), Formulier.class);

 return aux;
}


}