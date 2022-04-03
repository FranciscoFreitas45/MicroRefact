package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Form;
import com.ushahidi.swiftriver.core.Request.FormRequest;
public class FormRequestImpl implements FormRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Form getActionOnObj(Long idVMYL){
 Form aux = restTemplate.getForObject("http://0/FormActivity/{id}/Form/getActionOnObj",Form.class,idVMYL);
return aux;
}


public void setActionOnObj(Form actionOnObj,Long idVMYL){
 restTemplate.put("http://0/FormActivity/{id}/Form/setActionOnObj",actionOnObj,idVMYL);
 return ;
}


}