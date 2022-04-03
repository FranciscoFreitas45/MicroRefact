package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectDAOController {

 private ProjectDAO projectdao;


@GetMapping
("/findByProjectSprintsId")
public Set<Project> findByProjectSprintsId(@RequestParam(name = "id") Long id){
  return projectdao.findByProjectSprintsId(id);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return projectdao.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return projectdao.delete(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return projectdao.save(Object);
}


}