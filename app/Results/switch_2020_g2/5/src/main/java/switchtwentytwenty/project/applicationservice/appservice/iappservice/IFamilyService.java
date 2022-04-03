package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.dto.outdto.SystemRelationsOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import java.io.IOException;
import java.util.List;
public interface IFamilyService {


public List<FamilyOutDTO> getListOfFamilies()
;

public FamilyProfileOutDTO getFamilyProfile(String familyID)
;

public SystemRelationsOutDTO getSystemRelations()
;

}