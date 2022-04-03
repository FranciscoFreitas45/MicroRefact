package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectServiceController {

 private ProjectService projectservice;


@GetMapping
("/findAll")
public List<Project> findAll(){
  return projectservice.findAll();
}


@GetMapping
("/findOneById")
public Project findOneById(@RequestParam(name = "id") Long id){
  return projectservice.findOneById(id);
}


}