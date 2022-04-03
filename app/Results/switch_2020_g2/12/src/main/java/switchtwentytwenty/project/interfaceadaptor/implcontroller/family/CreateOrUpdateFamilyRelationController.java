package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.indto.FamilyRelationInDTO;
import switchtwentytwenty.project.dto.outdto.FamilyRelationOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.relation.ICreateOrUpdateFamilyRelationController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.member.ViewFamilyRelationsFromAPersonController;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CreateOrUpdateFamilyRelationController implements ICreateOrUpdateFamilyRelationController{

@Autowired
 private  IFamilyAndMemberService familyAndMemberService;


@PostMapping(value = "families/{familyID}/relations")
public ResponseEntity<Object> createOrUpdateFamilyRelation(String familyIdentifier,FamilyRelationInDTO info){
    String personEmail = info.getPersonEmail();
    String kinEmail = info.getKinEmail();
    String relationTypeName = info.getRelationType();
    try {
        Optional<FamilyRelationOutDTO> optional = familyAndMemberService.createFamilyRelation(personEmail, kinEmail, familyIdentifier, relationTypeName);
        if (optional.isPresent()) {
            Link selfLink = linkTo(methodOn(CreateOrUpdateFamilyRelationController.class).createOrUpdateFamilyRelation(familyIdentifier, info)).withRel("self");
            Link personLink = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personEmail)).withRel("relations");
            Link kinLink = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(kinEmail)).withRel("relations");
            Link relationsLink = WebMvcLinkBuilder.linkTo(methodOn(GetSystemRelationsListController.class).getSystemRelationsList()).withRel("system valid relations");
            FamilyRelationOutDTO dto = optional.get();
            dto.add(selfLink);
            dto.add(personLink);
            dto.add(kinLink);
            dto.add(relationsLink);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    } catch (AccountNotCreatedException | InstantiationException | ParseException | InvalidMovementTypeException | InvalidPersonNameException | InvalidVATException | InvalidRelationTypeException | IOException | InvalidEmailException | IllegalArgumentException exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}