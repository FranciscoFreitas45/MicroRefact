package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StoryDAOController {

 private StoryDAO storydao;


@GetMapping
("/findByStorySprintId")
public Set<Story> findByStorySprintId(@RequestParam(name = "idSprint") Long idSprint){
  return storydao.findByStorySprintId(idSprint);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return storydao.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return storydao.delete(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return storydao.save(Object);
}


}