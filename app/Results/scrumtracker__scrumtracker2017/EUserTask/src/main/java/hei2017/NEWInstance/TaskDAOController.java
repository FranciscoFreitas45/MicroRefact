package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskDAOController {

 private TaskDAO taskdao;


@GetMapping
("/findByTaskStoryId")
public Set<Task> findByTaskStoryId(@RequestParam(name = "idStory") Long idStory){
  return taskdao.findByTaskStoryId(idStory);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return taskdao.findAll(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return taskdao.findOne(Object);
}


@GetMapping
("/findOneById")
public Task findOneById(@RequestParam(name = "id") Long id){
  return taskdao.findOneById(id);
}


@GetMapping
("/findOneByNom")
public Task findOneByNom(@RequestParam(name = "nom") String nom){
  return taskdao.findOneByNom(nom);
}


@GetMapping
("/count")
public long count(){
  return taskdao.count();
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return taskdao.delete(Object);
}


@GetMapping
("/exists")
public Object exists(@RequestParam(name = "Object") Object Object){
  return taskdao.exists(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return taskdao.save(Object);
}


@GetMapping
("/findByTaskUsersId")
public Set<Task> findByTaskUsersId(@RequestParam(name = "id") Long id){
  return taskdao.findByTaskUsersId(id);
}


}