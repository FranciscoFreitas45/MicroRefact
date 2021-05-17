import org.springframework.web.client.RestTemplate;
public class CertificadoRequestImpl implements CertificadoRequest{

 private RestTemplate restTemplate;


public void setCertificados(List<Certificado> certificados,int pk_id_curso){
 restTemplate.put('http://teste/Curso/{id}/Certificado/setCertificados',certificados,pk_id_curso);
 return ;
}


public List<Certificado> getCertificados(int pk_id_curso){
 List<Certificado> aux = restTemplate.getForObject('http://teste/Curso/{id}/Certificado/getCertificados',List<Certificado>.class,pk_id_curso);
return aux;
}


}