package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICategoryRepositoryController {

 private ICategoryRepository icategoryrepository;


@GetMapping
("/findByID")
public Category findByID(@RequestParam(name = "id") CategoryID id){
  return icategoryrepository.findByID(id);
}


@GetMapping
("/save")
public Category save(@RequestParam(name = "category") Category category){
  return icategoryrepository.save(category);
}


@GetMapping
("/containsRootDesignation")
public boolean containsRootDesignation(@RequestParam(name = "designation") Designation designation){
  return icategoryrepository.containsRootDesignation(designation);
}


@GetMapping
("/containsDesignationWithSameParent")
public boolean containsDesignationWithSameParent(@RequestParam(name = "parentID") CategoryID parentID,@RequestParam(name = "designation") Designation designation){
  return icategoryrepository.containsDesignationWithSameParent(parentID,designation);
}


@GetMapping
("/getStandardCategories")
public List<Category> getStandardCategories(){
  return icategoryrepository.getStandardCategories();
}


@GetMapping
("/getListOfCategoriesWithSameParent")
public List<Category> getListOfCategoriesWithSameParent(@RequestParam(name = "parentID") CategoryID parentID){
  return icategoryrepository.getListOfCategoriesWithSameParent(parentID);
}


@GetMapping
("/getListOfStandardCategoriesWithSameParent")
public List<Category> getListOfStandardCategoriesWithSameParent(@RequestParam(name = "parentID") CategoryID parentID){
  return icategoryrepository.getListOfStandardCategoriesWithSameParent(parentID);
}


@GetMapping
("/getListOfFamilyCategories")
public List<Category> getListOfFamilyCategories(@RequestParam(name = "familyID") FamilyID familyID){
  return icategoryrepository.getListOfFamilyCategories(familyID);
}


}