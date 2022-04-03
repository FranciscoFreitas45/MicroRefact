package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto.FamilyOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.dto.outdto.SystemRelationsOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import switchtwentytwenty.project.Interface.IFamilyRepository;
@Service
@AllArgsConstructor
public class FamilyService implements IFamilyService{

@Autowired
 private  IFamilyRepository familyRepository;


public List<String> getSystemRelationsList(){
    List<String> systemRelations = new ArrayList<>();
    systemRelations.add(Constants.PARENT);
    systemRelations.add(Constants.CHILD);
    systemRelations.add(Constants.SPOUSE);
    systemRelations.add(Constants.SIBLING);
    systemRelations.add(Constants.UNCLE);
    systemRelations.add(Constants.NEPHEW);
    systemRelations.add(Constants.GRANDPARENT);
    systemRelations.add(Constants.GRANDCHILD);
    systemRelations.add(Constants.COUSIN);
    systemRelations.add(Constants.FRIEND);
    systemRelations.add(Constants.PARTNER);
    systemRelations.add(Constants.NOT_DEFINED);
    return systemRelations;
}


public List<FamilyOutDTO> getListOfFamilies(){
    List<FamilyOutDTO> familiesDTO = new ArrayList<>();
    List<Family> familyList = familyRepository.findAll();
    for (Family family : familyList) {
        FamilyOutDTO familyOutDTO = new FamilyOutDTO(family.getName().toString(), family.getID().toString());
        familiesDTO.add(familyOutDTO);
    }
    return familiesDTO;
}


public FamilyProfileOutDTO getFamilyProfile(String familyID){
    FamilyID id = new FamilyID(UUID.fromString(familyID));
    Family family = familyRepository.findByID(id);
    String familyName = family.getName().toString();
    String registrationDate = splitDate(family.getRegistrationDate().toString());
    String adminID = family.getAdministratorID().toString();
    return new FamilyProfileOutDTO(familyName, registrationDate, adminID);
}


public String splitDate(String completeRegistrationDate){
    String[] split = completeRegistrationDate.split(" ");
    return split[0];
}


public SystemRelationsOutDTO getSystemRelations(){
    List<String> relationsList = getSystemRelationsList();
    return new SystemRelationsOutDTO(relationsList);
}


}