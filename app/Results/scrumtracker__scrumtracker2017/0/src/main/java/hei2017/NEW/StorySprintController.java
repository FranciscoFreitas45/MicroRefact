package hei2017.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.entity.Story;
@RestController
@CrossOrigin
public class StorySprintController {

@Autowired
 private StorySprintService storysprintservice;


@GetMapping
("/Sprint/{id}/Story/getSprintStories")
public Set<Story> getSprintStories(@PathVariable(name="id") Long id){
return storysprintservice.getSprintStories(id);
}


@PutMapping
("/Sprint/{id}/Story/setSprintStories")
public void setSprintStories(@PathVariable(name="id") Long id,@RequestBody Set<Story> sprintStories){
storysprintservice.setSprintStories(id,sprintStories);
}


@GetMapping
("/Sprint/{id}/Story/addStory")
public Sprint addStory(@PathVariable(name="id") Long id,@RequestParam Story story){
return storysprintservice.addStory(id,story);
}


}