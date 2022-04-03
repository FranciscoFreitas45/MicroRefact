package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.Position;
import cn.gson.oasys.Request.PositionRequest;
public class PositionRequestImpl implements PositionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setPosition(Position position,Long id){
 restTemplate.put("http://5/User/{id}/Position/setPosition",position,id);
 return ;
}


public Position getPosition(Long id){
 Position aux = restTemplate.getForObject("http://5/User/{id}/Position/getPosition",Position.class,id);
return aux;
}


}