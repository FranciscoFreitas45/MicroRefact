package hei2017.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.entity.Project;
@RestController
@CrossOrigin
public class ProjectSprintController {

@Autowired
 private ProjectSprintService projectsprintservice;


@PutMapping
("/Sprint/{id}/Project/setSprintProject")
public void setSprintProject(@PathVariable(name="id") Long id,@RequestBody Project sprintProject){
projectsprintservice.setSprintProject(id,sprintProject);
}


@GetMapping
("/Sprint/{id}/Project/getSprintProject")
public Project getSprintProject(@PathVariable(name="id") Long id){
return projectsprintservice.getSprintProject(id);
}


}