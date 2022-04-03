package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SprintServiceController {

 private SprintService sprintservice;


@GetMapping
("/findOneById")
public Sprint findOneById(@RequestParam(name = "id") Long id){
  return sprintservice.findOneById(id);
}


@GetMapping
("/findAll")
public List<Sprint> findAll(){
  return sprintservice.findAll();
}


@GetMapping
("/findByProjectSprintIdWithStories")
public List<Sprint> findByProjectSprintIdWithStories(@RequestParam(name = "idProject") Long idProject){
  return sprintservice.findByProjectSprintIdWithStories(idProject);
}


}