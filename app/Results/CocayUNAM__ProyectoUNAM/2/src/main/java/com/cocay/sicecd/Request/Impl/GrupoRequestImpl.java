package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Grupo;
import com.cocay.sicecd.Request.GrupoRequest;
public class GrupoRequestImpl implements GrupoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setFk_id_grupo(Grupo fk_id_grupo,int pk_id_grupo){
 restTemplate.put("http://1/Certificado/{id}/Grupo/setFk_id_grupo",fk_id_grupo,pk_id_grupo);
 return ;
}


public Grupo getFk_id_grupo(int pk_id_grupo){
 Grupo aux = restTemplate.getForObject("http://1/Certificado/{id}/Grupo/getFk_id_grupo",Grupo.class,pk_id_grupo);
return aux;
}


}