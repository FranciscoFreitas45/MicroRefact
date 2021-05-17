import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CertificadoRepImpl implements CertificadoRep{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Certificado findCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findCertificado"))
    .queryParam("id_profesor",id_profesor)
    .queryParam("id_curso",id_curso)
    .queryParam("id_grupo",id_grupo);
  Certificado aux = restTemplate.getForObject(builder.toUriString(), Certificado.class)

 return aux;
}


public Certificado findCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findCertificado"))
    .queryParam("id_profesor",id_profesor)
    .queryParam("id_curso",id_curso)
    .queryParam("id_grupo",id_grupo);
  Certificado aux = restTemplate.getForObject(builder.toUriString(), Certificado.class)

 return aux;
}


}