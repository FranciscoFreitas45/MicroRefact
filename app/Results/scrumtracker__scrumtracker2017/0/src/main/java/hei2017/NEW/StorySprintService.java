package hei2017.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.dao.StoryDAO;
import hei2017.entity.Story;
@Service
public class StorySprintService {

@Autowired
 private StoryDAO storydao;


public Set<Story> getSprintStories(Long id){
return storydao.getSprintStories(id);
}


public void setSprintStories(Long id,Set<Story> sprintStories){
storydao.setSprintStories(id,sprintStories);
}


public Sprint addStory(Long id,Story story){
return storydao.addStory(id,story);
}


}