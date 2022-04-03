package hei2017.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.DTO.Project;
import hei2017.Request.ProjectRequest;
public class ProjectRequestImpl implements ProjectRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setSprintProject(Project sprintProject,Long id){
 restTemplate.put("http://3/Sprint/{id}/Project/setSprintProject",sprintProject,id);
 return ;
}


public Project getSprintProject(Long id){
 Project aux = restTemplate.getForObject("http://3/Sprint/{id}/Project/getSprintProject",Project.class,id);
return aux;
}


}