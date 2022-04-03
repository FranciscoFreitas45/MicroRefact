package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskController {

 private TaskDAO taskdao;


@PutMapping
("/setTaskStory/{id}")
public void setTaskStory(@PathVariable(name = "id") Long id,@RequestParam(name = "taskStory") Story taskStory){
 taskdao.setTaskStory(id,taskStory);
}


@PutMapping
("/setNom/{id}")
public void setNom(@PathVariable(name = "id") Long id,@RequestParam(name = "nom") String nom){
 taskdao.setNom(id,nom);
}


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") Long id,@RequestParam(name = "description") String description){
 taskdao.setDescription(id,description);
}


@PutMapping
("/setTempsDeCharge/{id}")
public void setTempsDeCharge(@PathVariable(name = "id") Long id,@RequestParam(name = "tempsDeCharge") Long tempsDeCharge){
 taskdao.setTempsDeCharge(id,tempsDeCharge);
}


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") StoryStatus status){
 taskdao.setStatus(id,status);
}


}