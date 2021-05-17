import org.springframework.web.client.RestTemplate;
public class CursoRequestImpl implements CursoRequest{

 private RestTemplate restTemplate;


public Curso getFk_id_curso(int int){
 Curso aux = restTemplate.getForObject('http://teste/Certificado/{id}/Curso/getFk_id_curso',Curso.class,int);
return aux;
}


public void setFk_id_curso(Curso fk_id_curso,int int){
 restTemplate.put('http://teste/Certificado/{id}/Curso/setFk_id_curso',fk_id_curso,int);
 return ;
}


}