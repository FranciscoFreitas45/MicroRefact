package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Manager;
import com.gp.cricket.Request.ManagerRequest;
public class ManagerRequestImpl implements ManagerRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Manager getManagerId(Integer managerIdv2){
 Manager aux = restTemplate.getForObject("http://5/Club/{id}/Manager/getManagerId",Manager.class,managerIdv2);
return aux;
}


public void setManagerId(Manager managerId,Integer managerIdv2){
 restTemplate.put("http://5/Club/{id}/Manager/setManagerId",managerId,managerIdv2);
 return ;
}


}