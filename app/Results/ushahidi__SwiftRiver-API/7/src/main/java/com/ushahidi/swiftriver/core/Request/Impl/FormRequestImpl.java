package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Form;
import com.ushahidi.swiftriver.core.Request.FormRequest;
public class FormRequestImpl implements FormRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setForms(List<Form> forms,long id){
 restTemplate.put("http://0/Account/{id}/Form/setForms",forms,id);
 return ;
}


public List<Form> getForms(long id){
 List<Form> aux = restTemplate.getForObject("http://0/Account/{id}/Form/getForms",List<Form>.class,id);
return aux;
}


}