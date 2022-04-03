package hei2017.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.entity.Sprint;
@RestController
@CrossOrigin
public class SprintStoryController {

@Autowired
 private SprintStoryService sprintstoryservice;


@GetMapping
("/Story/{id}/Sprint/getStorySprint")
public Sprint getStorySprint(@PathVariable(name="id") Long id){
return sprintstoryservice.getStorySprint(id);
}


@PutMapping
("/Story/{id}/Sprint/setStorySprint")
public void setStorySprint(@PathVariable(name="id") Long id,@RequestBody Sprint storySprint){
sprintstoryservice.setStorySprint(id,storySprint);
}


}