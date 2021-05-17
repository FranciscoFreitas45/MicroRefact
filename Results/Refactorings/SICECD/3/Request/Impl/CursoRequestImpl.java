import org.springframework.web.client.RestTemplate;
public class CursoRequestImpl implements CursoRequest{

 private RestTemplate restTemplate;


public Curso getFk_id_curso(int int){
 Curso aux = restTemplate.getForObject('http://teste/Grupo/{id}/Curso/getFk_id_curso',Curso.class,int);
return aux;
}


public void setFk_id_curso(Curso curso,int int){
 restTemplate.put('http://teste/Grupo/{id}/Curso/setFk_id_curso',curso,int);
 return ;
}


}