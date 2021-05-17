import org.springframework.web.client.RestTemplate;
public class GrupoRequestImpl implements GrupoRequest{

 private RestTemplate restTemplate;


public void setFk_id_grupo(Grupo fk_id_grupo,int int){
 restTemplate.put('http://teste/Certificado/{id}/Grupo/setFk_id_grupo',fk_id_grupo,int);
 return ;
}


public Grupo getFk_id_grupo(int int){
 Grupo aux = restTemplate.getForObject('http://teste/Certificado/{id}/Grupo/getFk_id_grupo',Grupo.class,int);
return aux;
}


}