package switchtwentytwenty.project.domain.domaindto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.Interface.Person;
import switchtwentytwenty.project.Interface.Ledger;
import switchtwentytwenty.project.Interface.Ledger;
@AllArgsConstructor
public class FamilyAndAdministratorDomainDTO {

@Getter
 private  Family family;

@Getter
 private  Person person;

@Getter
 private  Ledger personLedger;

@Getter
 private  Ledger familyLedger;


}