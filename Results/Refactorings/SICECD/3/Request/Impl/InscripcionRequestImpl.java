import org.springframework.web.client.RestTemplate;
public class InscripcionRequestImpl implements InscripcionRequest{

 private RestTemplate restTemplate;


public List<Inscripcion> getInscripciones(int pk_id_grupo){
 List<Inscripcion> aux = restTemplate.getForObject('http://teste/Grupo/{id}/Inscripcion/getInscripciones',List<Inscripcion>.class,pk_id_grupo);
return aux;
}


public void setInscripciones(List<Inscripcion> inscripciones,int pk_id_grupo){
 restTemplate.put('http://teste/Grupo/{id}/Inscripcion/setInscripciones',inscripciones,pk_id_grupo);
 return ;
}


}