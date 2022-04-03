package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.assembler.FamilyDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.domaindto.FamilyAndAdministratorDomainDTO;
import switchtwentytwenty.project.domain.domaindto.PersonAndLedgerDomainDTO;
import switchtwentytwenty.project.domain.domainservice.FamilyAndAdminFactory;
import switchtwentytwenty.project.domain.domainservice.PersonAndLedgerFactory;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.ID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.dto.outdto;
import switchtwentytwenty.project.dto.todomaindto;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.util.Util;
import java.io.IOException;
import java.util;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.ILedgerRepository;
import switchtwentytwenty.project.Interface.ILedgerIDGenerator;
import switchtwentytwenty.project.Interface.IAuthorizationService;
import switchtwentytwenty.project.DTO.LedgerID;
import switchtwentytwenty.project.DTO.Person;
@Service
@AllArgsConstructor
public class FamilyAndMemberService implements IFamilyAndMemberService{

@Autowired
 private  IFamilyRepository familyRepository;

@Autowired
 private  IPersonRepository personRepository;

@Autowired
 private  ILedgerRepository ledgerRepository;

@Autowired
 private  ILedgerIDGenerator ledgerIDGenerator;

@Autowired
 private  IFamilyIDGenerator familyIDGenerator;

@Autowired
 private  FamilyDomainDataAssembler familyDomainDataAssembler;

@Autowired
 private  IAuthorizationService signUpService;


public Optional<FamilyRelationOutDTO> familyRepositorySaveResponseHandler(FamilyJPA familyJPA,Email personID,Email kinID){
    FamilyJpaToDomainDTO familyJpaToDomainDTO = familyDomainDataAssembler.toDomain(familyJPA);
    Family storedFamily = FamilyFactory.create(familyJpaToDomainDTO);
    Optional<FamilyRelation> storedFamilyRelation = storedFamily.getFamilyRelationByIDs(personID, kinID);
    return storedFamilyRelation.map(FamilyRelationOutDTOMapper::toDTO);
}


@Transactional(rollbackFor = Exception.class)
public FamilyAndAdminOutDTO startFamily(FamilyAndAdminDTO dto){
    FamilyAndAdminVODTO valueObjects = FamilyAndAdminDomainAssembler.toDomain(dto);
    if (personRepository.existsByID(valueObjects.getEmail())) {
        throw new PersonAlreadyInSystemException("Email already used as ID.");
    }
    // create family and admin
    FamilyID familyID = familyIDGenerator.generate();
    LedgerID personLedgerID = ledgerIDGenerator.generate();
    LedgerID familyLedgerID;
    do {
        familyLedgerID = ledgerIDGenerator.generate();
    } while (personLedgerID.equals(familyLedgerID));
    FamilyAndAdminVODTO newValueObjects = valueObjects.addIDs(familyID, familyLedgerID, personLedgerID);
    PersonVoDTO personVoDTO = FamilyAndAdminDomainDTOMapper.createVOPersonDTO(newValueObjects);
    FamilyVoDTO familyVoDTO = FamilyAndAdminDomainDTOMapper.createVOFamilyDTO(newValueObjects);
    FamilyAndAdministratorDomainDTO domainDTO = FamilyAndAdminFactory.startFamily(familyVoDTO, personVoDTO);
    familyRepository.save(domainDTO.getFamily());
    personRepository.save(domainDTO.getPerson());
    ledgerRepository.save(domainDTO.getPersonLedger());
    ledgerRepository.save(domainDTO.getFamilyLedger());
    FamilyAndAdminOutDTO familyAndAdminInfo;
    familyAndAdminInfo = new FamilyAndAdminOutDTO(newValueObjects.getFamilyName().toString(), newValueObjects.getPersonName().toString(), newValueObjects.getFamilyID().toString(), newValueObjects.getEmail().toString());
    // create user
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_ADMIN.name());
    SignupDTO registerUserDTO = new SignupDTO(dto.getUsername(), dto.getEmail(), dto.getPassword(), domainDTO.getFamily().getID().toString(), roles);
    signUpService.registerUser(registerUserDTO);
    return familyAndAdminInfo;
}


@Transactional(rollbackFor = Exception.class)
public PersonOutDTO addFamilyMember(PersonDTO personDTO){
    // create family member
    LedgerID ledgerID = ledgerIDGenerator.generate();
    PersonVoDTO personVoDTO = PersonDomainAssembler.toDomain(personDTO, ledgerID);
    if (!familyRepository.existsByID(personVoDTO.getFamilyID())) {
        throw new ElementNotFoundException(Constants.FAMILY_NOT_FOUND);
    }
    if (personRepository.existsByID(personVoDTO.getEmail())) {
        throw new PersonAlreadyInSystemException("Email Already Used");
    }
    if (personRepository.existsByFamilyIDAndVat(personVoDTO.getFamilyID(), personVoDTO.getVat())) {
        throw new InvalidVATException("Vat Already Used In This Family");
    }
    PersonAndLedgerDomainDTO personAndLedgerDomainDTO = PersonAndLedgerFactory.create(personVoDTO);
    Person person = personAndLedgerDomainDTO.getPerson();
    personRepository.save(personAndLedgerDomainDTO.getPerson());
    ledgerRepository.save(personAndLedgerDomainDTO.getLedger());
    // create user
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_USER.name());
    SignupDTO registerUserDTO = new SignupDTO(personDTO.getUsername(), personDTO.getEmail(), personDTO.getPassword(), personDTO.getFamilyID(), roles);
    signUpService.registerUser(registerUserDTO);
    return new PersonOutDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
}


public void areTheSamePerson(ID personID,ID kinID){
    if (personID.equals(kinID)) {
        throw new IllegalArgumentException("Person ID and kin Id are from the same person");
    }
}


public Optional<FamilyRelationOutDTO> createFamilyRelation(String personEmail,String kinEmail,String familyIdentifier,String relationTypeName){
    Email personID = new Email(personEmail);
    Email kinID = new Email(kinEmail);
    FamilyID familyID = new FamilyID(UUID.fromString(familyIdentifier));
    RelationType relationType = RelationType.getInstance(relationTypeName);
    if (areFromTheSameHousehold(personID, kinID, familyID)) {
        Family family = familyRepository.findByID(familyID);
        family.createFamilyRelation(personID, kinID, relationType);
        FamilyJPA familyJPA = familyRepository.save(family);
        return familyRepositorySaveResponseHandler(familyJPA, personID, kinID);
    }
    return Optional.empty();
}


public boolean areFromTheSameHousehold(Email personID,Email kinID,FamilyID familyID){
    Util.checkIfNull(Arrays.asList(personID, kinID, familyID));
    areTheSamePerson(personID, kinID);
    Person person = personRepository.findByID(personID);
    Person kin = personRepository.findByID(kinID);
    if (!person.isMemberOfFamily(familyID) || !kin.isMemberOfFamily(familyID)) {
        throw new IllegalArgumentException("Person and/or kin doesn't belong to the family");
    }
    return true;
}


public List<FamilyRelation> getFamilyRelationByPersonID(Email personID,FamilyID familyID){
    Family family = familyRepository.findByID(familyID);
    return family.getFamilyRelationByPersonID(personID);
}


}