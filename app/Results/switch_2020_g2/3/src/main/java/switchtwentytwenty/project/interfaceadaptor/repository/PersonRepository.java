package switchtwentytwenty.project.interfaceadaptor.repository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.datamodel.AccountIDJPA;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.assembler.PersonDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.PersonJPARepository;
import java.util.List;
import java.util.Optional;
import switchtwentytwenty.project.DTO.AccountID;
import switchtwentytwenty.project.DTO.FamilyID;
@Service
public class PersonRepository implements IPersonRepository{

@Autowired
 private PersonJPARepository repository;

@Autowired
 private PersonDomainDataAssembler assembler;


public boolean existsByID(Email id){
    return this.repository.existsById(id.toString());
}


@Transactional
public Person findByAccountID(AccountID accountID){
    List<PersonJPA> list = (List<PersonJPA>) repository.findAll();
    for (PersonJPA personJPA : list) {
        List<AccountIDJPA> listAccounts = personJPA.getAccountIDList();
        for (AccountIDJPA accountIDJPA : listAccounts) {
            if (accountIDJPA.hasSameID(accountID.toString())) {
                return PersonFactory.create(assembler.toDomain(personJPA));
            }
        }
    }
    throw new ElementNotFoundException("Person not found");
}


@Transactional
public Person findByID(Email id){
    Optional<PersonJPA> personJPA = this.repository.findById(id.toString());
    if (!personJPA.isPresent()) {
        throw new ElementNotFoundException("Person not found");
    }
    PersonJpaToDomainDTO dto = assembler.toDomain(personJPA.get());
    return PersonFactory.create(dto);
}


@Transactional
public PersonList findByFamilyID(FamilyID familyID){
    Iterable<PersonJPA> personJPAList = this.repository.findAllByFamilyID(familyID.toString());
    PersonList result = new PersonList();
    for (PersonJPA personJPA : personJPAList) {
        PersonJpaToDomainDTO dto = assembler.toDomain(personJPA);
        result.add(PersonFactory.create(dto));
    }
    return result;
}


public void save(Person person){
    PersonJPA personJPA = assembler.toData(person);
    repository.save(personJPA);
}


@Override
public void deleteAll(){
    this.repository.deleteAll();
}


@Transactional
public boolean existsByFamilyIDAndVat(FamilyID familyID,VAT vat){
    return this.repository.existsByFamilyIDAndVat(familyID.toString(), vat.toString());
}


}