package com.fzshuai.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.DTO.Type;
import com.fzshuai.Request.TypeRequest;
public class TypeRequestImpl implements TypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setType(Type type,Long id){
 restTemplate.put("http://3/Blog/{id}/Type/setType",type,id);
 return ;
}


public Type getType(Long id){
 Type aux = restTemplate.getForObject("http://3/Blog/{id}/Type/getType",Type.class,id);
return aux;
}


}