package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.SystemRelationsOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.relation.IGetSystemRelationsListController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetSystemRelationsListController implements IGetSystemRelationsListController{

@Autowired
 private  IFamilyService familyService;


@GetMapping("/families/relations/")
public ResponseEntity<Object> getSystemRelationsList(){
    SystemRelationsOutDTO systemRelationsOutDTO = familyService.getSystemRelations();
    Link linkToRelations = linkTo(methodOn(GetSystemRelationsListController.class).getSystemRelationsList()).withRel("view system relations");
    systemRelationsOutDTO.add(linkToRelations);
    return new ResponseEntity<>(systemRelationsOutDTO, HttpStatus.OK);
}


}