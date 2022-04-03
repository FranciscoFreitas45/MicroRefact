package hei2017.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.DTO.Sprint;
import hei2017.Request.SprintRequest;
public class SprintRequestImpl implements SprintRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Sprint> getProjectSprints(Long id){
 Set<Sprint> aux = restTemplate.getForObject("http://1/Project/{id}/Sprint/getProjectSprints",Set<Sprint>.class,id);
return aux;
}


public void setProjectSprints(Set<Sprint> projectSprints,Long id){
 restTemplate.put("http://1/Project/{id}/Sprint/setProjectSprints",projectSprints,id);
 return ;
}


public void addSprint(Sprint sprint,Long id){
 restTemplate.put("http://1/Project/{id}/Sprint/addSprint",sprint,id);
 return ;
}


}