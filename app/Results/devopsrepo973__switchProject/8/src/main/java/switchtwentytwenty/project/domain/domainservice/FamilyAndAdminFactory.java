package switchtwentytwenty.project.domain.domainservice;
 import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.ledger.LedgerFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.domaindto.FamilyAndAdministratorDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidVATException;
public class FamilyAndAdminFactory {

/**
 * Private constructor.
 */
private FamilyAndAdminFactory() {
}
public FamilyAndAdministratorDomainDTO startFamily(FamilyVoDTO familyValueObjects,PersonVoDTO personValueObjects){
    Family family = FamilyFactory.create(familyValueObjects);
    Person person = PersonFactory.create(personValueObjects);
    Ledger personLedger = LedgerFactory.create(personValueObjects.getLedgerID());
    Ledger familyLedger = LedgerFactory.create(family.getLedgerID());
    return new FamilyAndAdministratorDomainDTO(family, person, personLedger, familyLedger);
}


}