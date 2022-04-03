package hei2017.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.DTO.Sprint;
import hei2017.Request.SprintRequest;
public class SprintRequestImpl implements SprintRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Sprint getStorySprint(Long id){
 Sprint aux = restTemplate.getForObject("http://1/Story/{id}/Sprint/getStorySprint",Sprint.class,id);
return aux;
}


public void setStorySprint(Sprint storySprint,Long id){
 restTemplate.put("http://1/Story/{id}/Sprint/setStorySprint",storySprint,id);
 return ;
}


}