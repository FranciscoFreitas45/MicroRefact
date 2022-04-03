package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IFamilyRepositoryController {

 private IFamilyRepository ifamilyrepository;


@GetMapping
("/findByID")
public Family findByID(@RequestParam(name = "id") FamilyID id){
  return ifamilyrepository.findByID(id);
}


@GetMapping
("/save")
public FamilyJPA save(@RequestParam(name = "entity") Family entity){
  return ifamilyrepository.save(entity);
}


@GetMapping
("/findAll")
public List<Family> findAll(){
  return ifamilyrepository.findAll();
}


}