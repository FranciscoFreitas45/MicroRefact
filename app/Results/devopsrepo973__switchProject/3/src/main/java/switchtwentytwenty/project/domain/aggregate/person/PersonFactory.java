package switchtwentytwenty.project.domain.aggregate.person;
 import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import java.util.Objects;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonFactory {


public Person create(Email email,PersonName personName,VAT vat,BirthDate birthDate,TelephoneNumberList telephoneNumberList,Address address,FamilyID familyID,LedgerID ledgerID){
    Person person = new Person(email);
    person.setName(personName);
    person.setVat(vat);
    person.setBirthDate(birthDate);
    person.setTelephoneNumbers(telephoneNumberList);
    person.setAddress(address);
    person.setFamilyID(familyID);
    person.setLedgerID(ledgerID);
    Objects.requireNonNull(person.getName());
    Objects.requireNonNull(person.getBirthDate());
    Objects.requireNonNull(person.getVat());
    Objects.requireNonNull(person.getAddress());
    Objects.requireNonNull(person.getFamilyID());
    Objects.requireNonNull(person.getLedgerID());
    return person;
}


}