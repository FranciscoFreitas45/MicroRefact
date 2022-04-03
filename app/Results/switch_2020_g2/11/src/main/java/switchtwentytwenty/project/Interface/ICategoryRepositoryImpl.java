package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.ICategoryRepository;
public class ICategoryRepositoryImpl implements ICategoryRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public boolean containsRootDesignation(Designation designation){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/containsRootDesignation"))
    .queryParam("designation",designation)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Category save(Category category){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("category",category)
;  Category aux = restTemplate.getForObject(builder.toUriString(), Category.class);

 return aux;
}


public Category findByID(CategoryID id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByID"))
    .queryParam("id",id)
;  Category aux = restTemplate.getForObject(builder.toUriString(), Category.class);

 return aux;
}


public boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/containsDesignationWithSameParent"))
    .queryParam("parentID",parentID)
    .queryParam("designation",designation)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public List<Category> getStandardCategories(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getStandardCategories"))
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


public List<Category> getListOfCategoriesWithSameParent(CategoryID parentID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListOfCategoriesWithSameParent"))
    .queryParam("parentID",parentID)
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


public List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListOfStandardCategoriesWithSameParent"))
    .queryParam("parentID",parentID)
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


public List<Category> getListOfFamilyCategories(FamilyID familyID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListOfFamilyCategories"))
    .queryParam("familyID",familyID)
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


}