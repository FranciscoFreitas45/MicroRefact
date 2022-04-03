package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StoryController {

 private StoryDAO storydao;


@PutMapping
("/setStorySprint/{id}")
public void setStorySprint(@PathVariable(name = "id") Long id,@RequestParam(name = "storySprint") Sprint storySprint){
 storydao.setStorySprint(id,storySprint);
}


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") Long id,@RequestParam(name = "description") String description){
 storydao.setDescription(id,description);
}


@PutMapping
("/setDateCreation/{id}")
public void setDateCreation(@PathVariable(name = "id") Long id,@RequestParam(name = "dateCreation") Timestamp dateCreation){
 storydao.setDateCreation(id,dateCreation);
}


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Long id,@RequestParam(name = "status") StoryStatus status){
 storydao.setStatus(id,status);
}


@PutMapping
("/setPoints/{id}")
public void setPoints(@PathVariable(name = "id") Long id,@RequestParam(name = "points") Integer points){
 storydao.setPoints(id,points);
}


}