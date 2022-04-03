package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Grupo;
import co.edu.uniquindio.gri.Request.GrupoRequest;
public class GrupoRequestImpl implements GrupoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setGrupo(List<Grupo> grupo,long id){
 restTemplate.put("http://2/Centro/{id}/Grupo/setGrupo",grupo,id);
 return ;
}


public List<Grupo> getGrupo(long id){
 List<Grupo> aux = restTemplate.getForObject("http://2/Centro/{id}/Grupo/getGrupo",List<Grupo>.class,id);
return aux;
}


}