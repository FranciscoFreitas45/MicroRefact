package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SprintDAOController {

 private SprintDAO sprintdao;


@GetMapping
("/findBySprintStoriesId")
public Sprint findBySprintStoriesId(@RequestParam(name = "id") Long id){
  return sprintdao.findBySprintStoriesId(id);
}


@GetMapping
("/findBySprintProjectId")
public Set<Sprint> findBySprintProjectId(@RequestParam(name = "idProject") Long idProject){
  return sprintdao.findBySprintProjectId(idProject);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sprintdao.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return sprintdao.delete(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sprintdao.save(Object);
}


}