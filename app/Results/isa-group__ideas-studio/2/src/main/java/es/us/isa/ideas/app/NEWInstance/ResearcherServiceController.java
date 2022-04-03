package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResearcherServiceController {

 private ResearcherService researcherservice;


@PutMapping
("/save")
public void save(@RequestParam(name = "researcher") Researcher researcher){
researcherservice.save(researcher);
}


}