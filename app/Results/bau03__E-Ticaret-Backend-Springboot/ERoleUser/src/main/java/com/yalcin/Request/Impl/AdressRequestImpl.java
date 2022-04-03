package com.yalcin.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.DTO.Adress;
import com.yalcin.Request.AdressRequest;
public class AdressRequestImpl implements AdressRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setAdress(Set<Adress> adress,Integer id){
 restTemplate.put("http://2/User/{id}/Adress/setAdress",adress,id);
 return ;
}


public Set<Adress> getAdress(Integer id){
 Set<Adress> aux = restTemplate.getForObject("http://2/User/{id}/Adress/getAdress",Set<Adress>.class,id);
return aux;
}


}