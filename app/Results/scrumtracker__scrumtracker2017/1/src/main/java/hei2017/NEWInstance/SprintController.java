package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SprintController {

 private SprintDAO sprintdao;


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") Long id,@RequestParam(name = "description") String description){
 sprintdao.setDescription(id,description);
}


@PutMapping
("/setDateCreation/{id}")
public void setDateCreation(@PathVariable(name = "id") Long id,@RequestParam(name = "dateCreation") Timestamp dateCreation){
 sprintdao.setDateCreation(id,dateCreation);
}


@PutMapping
("/setDateDebut/{id}")
public void setDateDebut(@PathVariable(name = "id") Long id,@RequestParam(name = "dateDebut") Timestamp dateDebut){
 sprintdao.setDateDebut(id,dateDebut);
}


@PutMapping
("/setDateFin/{id}")
public void setDateFin(@PathVariable(name = "id") Long id,@RequestParam(name = "dateFin") Timestamp dateFin){
 sprintdao.setDateFin(id,dateFin);
}


@PutMapping
("/setSprintProject/{id}")
public void setSprintProject(@PathVariable(name = "id") Long id,@RequestParam(name = "sprintProject") Project sprintProject){
 sprintdao.setSprintProject(id,sprintProject);
}


}