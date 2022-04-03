package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.CreepersListFundDao;
public class CreepersListFundDaoImpl implements CreepersListFundDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://17";


public List<TCreepersListFund> queryListByUserCode(String userCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryListByUserCode"))
    .queryParam("userCode",userCode)
;  List<TCreepersListFund> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListFund>.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public TCreepersListFund findTop1ByUserCode(String userCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTop1ByUserCode"))
    .queryParam("userCode",userCode)
;  TCreepersListFund aux = restTemplate.getForObject(builder.toUriString(), TCreepersListFund.class);

 return aux;
}


public void updateListByUserCode(String userCode,String flag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateListByUserCode"))
    .queryParam("userCode",userCode)
    .queryParam("flag",flag)
;
  restTemplate.put(builder.toUriString(), null);
}


}