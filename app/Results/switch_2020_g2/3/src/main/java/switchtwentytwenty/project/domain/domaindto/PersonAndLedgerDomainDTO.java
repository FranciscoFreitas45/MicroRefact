package switchtwentytwenty.project.domain.domaindto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.Interface.Ledger;
@AllArgsConstructor
public class PersonAndLedgerDomainDTO {

@Getter
 private  Person person;

@Getter
 private  Ledger ledger;


}