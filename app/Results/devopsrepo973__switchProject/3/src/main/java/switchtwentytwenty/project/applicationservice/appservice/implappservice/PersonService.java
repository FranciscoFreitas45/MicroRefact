package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.exception;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class PersonService implements IPersonService{

@Autowired
 private  IPersonRepository personRepository;


public List<PersonOutDTO> getListOfFamilyMembers(String familyID){
    FamilyID famID = new FamilyID(UUID.fromString(familyID));
    List<PersonOutDTO> familyMembers = new ArrayList<>();
    PersonList members = personRepository.findByFamilyID(famID);
    List<Person> users = members.getPersonList();
    for (Person person : users) {
        PersonOutDTO user = new PersonOutDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
        familyMembers.add(user);
    }
    return familyMembers;
}


@Transactional(rollbackFor = Exception.class)
public boolean deleteEmailFromProfile(String personId,String otherEmail){
    Email personID = new Email(personId);
    Email emailToDelete = new Email(otherEmail);
    Person person = personRepository.findByID(personID);
    person.removeEmail(emailToDelete);
    personRepository.save(person);
    return true;
}


@Transactional(rollbackFor = Exception.class)
public boolean addEmailToProfile(String personId,String emailToAdd){
    Email personID = new Email(personId);
    Email inputEmail = new Email(emailToAdd);
    Person person = personRepository.findByID(personID);
    person.addEmail(inputEmail);
    personRepository.save(person);
    return true;
}


public UserProfileOutDTO getUserProfile(String personId){
    Email parseId = new Email(personId);
    Person person = personRepository.findByID(parseId);
    return person.getProfile();
}


}