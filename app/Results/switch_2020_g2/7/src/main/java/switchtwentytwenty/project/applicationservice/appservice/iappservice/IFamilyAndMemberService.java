package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.outdto.FamilyRelationOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.outdto.ViewRelationOutDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.dto.outdto.FamilyAndAdminOutDTO;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
public interface IFamilyAndMemberService {


public PersonOutDTO addFamilyMember(PersonDTO personDTO)
;

public FamilyAndAdminOutDTO startFamily(FamilyAndAdminDTO dto)
;

public Optional<FamilyRelationOutDTO> createFamilyRelation(String personEmail,String kinEmail,String familyIdentifier,String relationTypeName)
;

public ViewRelationOutDTO getFamilyRelationByPersonID(String personID)
;

}