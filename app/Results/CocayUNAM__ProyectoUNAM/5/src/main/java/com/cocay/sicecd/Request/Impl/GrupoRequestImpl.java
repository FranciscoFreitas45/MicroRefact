package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Grupo;
import com.cocay.sicecd.Request.GrupoRequest;
public class GrupoRequestImpl implements GrupoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setGrupos(List<Grupo> grupos,int pk_id_curso){
 restTemplate.put("http://1/Curso/{id}/Grupo/setGrupos",grupos,pk_id_curso);
 return ;
}


public void setFk_id_grupo(Grupo fk_id_grupo,int pk_id_curso){
 restTemplate.put("http://1/Curso/{id}/Grupo/setFk_id_grupo",fk_id_grupo,pk_id_curso);
 return ;
}


public List<Grupo> getGrupos(int pk_id_curso){
 List<Grupo> aux = restTemplate.getForObject("http://1/Curso/{id}/Grupo/getGrupos",List<Grupo>.class,pk_id_curso);
return aux;
}


public Grupo getFk_id_grupo(int pk_id_curso){
 Grupo aux = restTemplate.getForObject("http://1/Curso/{id}/Grupo/getFk_id_grupo",Grupo.class,pk_id_curso);
return aux;
}


}