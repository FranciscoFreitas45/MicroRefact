package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.exception;
import java.util.List;
public interface IPersonService {


public List<PersonOutDTO> getListOfFamilyMembers(String familyID)
;

public boolean deleteEmailFromProfile(String personId,String otherEmail)
;

public boolean addEmailToProfile(String personID,String emailToInput)
;

public UserProfileOutDTO getUserProfile(String personId)
;

}