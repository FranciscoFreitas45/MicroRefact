import org.springframework.web.client.RestTemplate;
public class CertificadoRequestImpl implements CertificadoRequest{

 private RestTemplate restTemplate;


public void setCertificados(List<Certificado> certificados,int pk_id_profesor){
 restTemplate.put('http://teste/Profesor/{id}/Certificado/setCertificados',certificados,pk_id_profesor);
 return ;
}


public List<Certificado> getCertificados(int pk_id_profesor){
 List<Certificado> aux = restTemplate.getForObject('http://teste/Profesor/{id}/Certificado/getCertificados',List<Certificado>.class,pk_id_profesor);
return aux;
}


}