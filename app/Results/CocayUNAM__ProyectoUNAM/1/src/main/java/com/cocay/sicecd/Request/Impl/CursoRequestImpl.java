package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Curso;
import com.cocay.sicecd.Request.CursoRequest;
public class CursoRequestImpl implements CursoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Curso getFk_id_curso(int pk_id_curso){
 Curso aux = restTemplate.getForObject("http://5/Grupo/{id}/Curso/getFk_id_curso",Curso.class,pk_id_curso);
return aux;
}


public void setFk_id_curso(Curso curso,int pk_id_curso){
 restTemplate.put("http://5/Grupo/{id}/Curso/setFk_id_curso",curso,pk_id_curso);
 return ;
}


}