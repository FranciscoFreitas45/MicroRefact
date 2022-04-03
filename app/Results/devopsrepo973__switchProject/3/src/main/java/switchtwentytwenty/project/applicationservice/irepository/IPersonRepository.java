package switchtwentytwenty.project.applicationservice.irepository;
 import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.exception;
public interface IPersonRepository {


public boolean existsByID(Email id)
;

public Person findByAccountID(AccountID accountID)
;

public PersonList findByFamilyID(FamilyID familyID)
;

public Person findByID(Email id)
;

public void save(Person entity)
;

public void deleteAll()
;

public boolean existsByFamilyIDAndVat(FamilyID familyID,VAT vat)
;

}