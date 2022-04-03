package hei2017.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.DTO.Story;
import hei2017.Request.StoryRequest;
public class StoryRequestImpl implements StoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Story> getSprintStories(Long id){
 Set<Story> aux = restTemplate.getForObject("http://0/Sprint/{id}/Story/getSprintStories",Set<Story>.class,id);
return aux;
}


public void setSprintStories(Set<Story> sprintStories,Long id){
 restTemplate.put("http://0/Sprint/{id}/Story/setSprintStories",sprintStories,id);
 return ;
}


public Sprint addStory(Story story,Long id){
 Sprint aux = restTemplate.getForObject("http://0/Sprint/{id}/Story/addStory?'story'=story&'id'=id',",Sprint.class,id);
return aux;
}


}