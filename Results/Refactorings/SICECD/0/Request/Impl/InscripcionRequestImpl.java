import org.springframework.web.client.RestTemplate;
public class InscripcionRequestImpl implements InscripcionRequest{

 private RestTemplate restTemplate;


public List<Inscripcion> getInscripciones(int pk_id_profesor){
 List<Inscripcion> aux = restTemplate.getForObject('http://teste/Profesor/{id}/Inscripcion/getInscripciones',List<Inscripcion>.class,pk_id_profesor);
return aux;
}


public void setInscripciones(List<Inscripcion> inscripciones,int pk_id_profesor){
 restTemplate.put('http://teste/Profesor/{id}/Inscripcion/setInscripciones',inscripciones,pk_id_profesor);
 return ;
}


}