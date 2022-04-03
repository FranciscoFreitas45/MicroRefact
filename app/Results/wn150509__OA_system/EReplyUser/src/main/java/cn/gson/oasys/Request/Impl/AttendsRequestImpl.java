package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.Attends;
import cn.gson.oasys.Request.AttendsRequest;
public class AttendsRequestImpl implements AttendsRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Attends> getaSet(Long userId){
 Set<Attends> aux = restTemplate.getForObject("http://9/User/{id}/Attends/getaSet",Set<Attends>.class,userId);
return aux;
}


public void setaSet(Set<Attends> aSet,Long userId){
 restTemplate.put("http://9/User/{id}/Attends/setaSet",aSet,userId);
 return ;
}


}