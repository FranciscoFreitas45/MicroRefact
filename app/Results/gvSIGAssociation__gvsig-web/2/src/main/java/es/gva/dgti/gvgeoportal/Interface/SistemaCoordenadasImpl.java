package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.SistemaCoordenadas;
public class SistemaCoordenadasImpl implements SistemaCoordenadas{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}