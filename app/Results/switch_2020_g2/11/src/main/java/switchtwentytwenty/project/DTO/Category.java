package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
public interface Category extends AggregateRoot<Category, CategoryID>{

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public Designation getDesignation()
;

public CategoryID getID()
;

public boolean hasSameDesignation(Designation designation)
;

public FamilyID getFamilyID()
;

public CategoryID getParentID()
;

public boolean isStandard()

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isStandard"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}
;

}