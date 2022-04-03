package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.BatmanType;
import com.gp.cricket.Request.BatmanTypeRequest;
public class BatmanTypeRequestImpl implements BatmanTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public BatmanType getBatmanTypeId(Integer batmanTypeIdv2){
 BatmanType aux = restTemplate.getForObject("http://7/Player/{id}/BatmanType/getBatmanTypeId",BatmanType.class,batmanTypeIdv2);
return aux;
}


public void setBatmanTypeId(BatmanType batmanTypeId,Integer batmanTypeIdv2){
 restTemplate.put("http://7/Player/{id}/BatmanType/setBatmanTypeId",batmanTypeId,batmanTypeIdv2);
 return ;
}


}