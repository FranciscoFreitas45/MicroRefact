import org.springframework.web.client.RestTemplate;
public class ProfesorRequestImpl implements ProfesorRequest{

 private RestTemplate restTemplate;


public Profesor getFk_id_profesor(int int){
 Profesor aux = restTemplate.getForObject('http://teste/Certificado/{id}/Profesor/getFk_id_profesor',Profesor.class,int);
return aux;
}


public void setFk_id_profesor(Profesor fk_id_profesor,int int){
 restTemplate.put('http://teste/Certificado/{id}/Profesor/setFk_id_profesor',fk_id_profesor,int);
 return ;
}


}