package hei2017.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.entity.Sprint;
@RestController
@CrossOrigin
public class SprintProjectController {

@Autowired
 private SprintProjectService sprintprojectservice;


@GetMapping
("/Project/{id}/Sprint/getProjectSprints")
public Set<Sprint> getProjectSprints(@PathVariable(name="id") Long id){
return sprintprojectservice.getProjectSprints(id);
}


@PutMapping
("/Project/{id}/Sprint/setProjectSprints")
public void setProjectSprints(@PathVariable(name="id") Long id,@RequestBody Set<Sprint> projectSprints){
sprintprojectservice.setProjectSprints(id,projectSprints);
}


@PutMapping
("/Project/{id}/Sprint/addSprint")
public void addSprint(@PathVariable(name="id") Long id,@RequestBody Sprint sprint){
sprintprojectservice.addSprint(id,sprint);
}


}