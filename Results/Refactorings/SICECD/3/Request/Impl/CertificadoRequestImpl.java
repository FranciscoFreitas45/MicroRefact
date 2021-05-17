import org.springframework.web.client.RestTemplate;
public class CertificadoRequestImpl implements CertificadoRequest{

 private RestTemplate restTemplate;


public void setCertificados(List<Certificado> certificados,int pk_id_grupo){
 restTemplate.put('http://teste/Grupo/{id}/Certificado/setCertificados',certificados,pk_id_grupo);
 return ;
}


public List<Certificado> getCertificados(int pk_id_grupo){
 List<Certificado> aux = restTemplate.getForObject('http://teste/Grupo/{id}/Certificado/getCertificados',List<Certificado>.class,pk_id_grupo);
return aux;
}


}