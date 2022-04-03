package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StoryServiceController {

 private StoryService storyservice;


@GetMapping
("/findByStorySprintWithTask")
public Set<Story> findByStorySprintWithTask(@RequestParam(name = "idSprint") Long idSprint){
  return storyservice.findByStorySprintWithTask(idSprint);
}


@GetMapping
("/findOneByIdWithAll")
public Story findOneByIdWithAll(@RequestParam(name = "id") Long id){
  return storyservice.findOneByIdWithAll(id);
}


@GetMapping
("/save")
public Story save(@RequestParam(name = "story") Story story){
  return storyservice.save(story);
}


@GetMapping
("/findAll")
public List<Story> findAll(){
  return storyservice.findAll();
}


@GetMapping
("/findAllWithoutSprint")
public List<Story> findAllWithoutSprint(){
  return storyservice.findAllWithoutSprint();
}


}