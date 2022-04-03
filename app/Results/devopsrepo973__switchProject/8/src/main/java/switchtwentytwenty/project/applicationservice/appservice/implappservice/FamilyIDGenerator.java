package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import java.util.UUID;
@Service
@AllArgsConstructor
public class FamilyIDGenerator implements IFamilyIDGenerator{

@Autowired
 private IFamilyRepository familyRepository;


public FamilyID generate(){
    UUID id;
    FamilyID familYID;
    do {
        id = UUID.randomUUID();
        familYID = new FamilyID(id);
    } while (familyRepository.existsByID(familYID));
    return familYID;
}


}