package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectController {

 private ProjectDAO projectdao;


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") Long id,@RequestParam(name = "description") String description){
 projectdao.setDescription(id,description);
}


@PutMapping
("/setDateCreation/{id}")
public void setDateCreation(@PathVariable(name = "id") Long id,@RequestParam(name = "dateCreation") Timestamp dateCreation){
 projectdao.setDateCreation(id,dateCreation);
}


}