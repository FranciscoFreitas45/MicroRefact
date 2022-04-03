package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.BallerType;
import com.gp.cricket.Request.BallerTypeRequest;
public class BallerTypeRequestImpl implements BallerTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public BallerType getBallerTypeId(Integer ballerTypeIdv2){
 BallerType aux = restTemplate.getForObject("http://6/Player/{id}/BallerType/getBallerTypeId",BallerType.class,ballerTypeIdv2);
return aux;
}


public void setBallerTypeId(BallerType ballerTypeId,Integer ballerTypeIdv2){
 restTemplate.put("http://6/Player/{id}/BallerType/setBallerTypeId",ballerTypeId,ballerTypeIdv2);
 return ;
}


}