package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.exception.InvalidDateException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidPersonNameException;
import switchtwentytwenty.project.exception.InvalidVATException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class PersonDomainDataAssembler {


public PersonJpaToDomainDTO toDomain(PersonJPA personJPA){
    Email mainEmail = new Email(personJPA.getMainEmail());
    PersonName personName = new PersonName(personJPA.getName());
    VAT vat = new VAT(personJPA.getVat());
    BirthDate birthDate = new BirthDate(personJPA.getBirthDate());
    Address address = AddressDomainAssembler.toDomain(personJPA.getAddress());
    FamilyID familyID = new FamilyID(UUID.fromString(personJPA.getFamilyID()));
    LedgerID ledgerID = new LedgerID(UUID.fromString(personJPA.getLedgerID()));
    TelephoneNumberList telephoneNumbersList = TelephoneNumberDomainAssembler.toDomain(personJPA.getTelephoneNumbers());
    List<AccountIDJPA> accountIDJPAList = personJPA.getAccountIDList();
    AccountIDList accountIDList = new AccountIDList();
    for (AccountIDJPA accountIDJPA : accountIDJPAList) {
        accountIDList.add(new AccountID(UUID.fromString(accountIDJPA.getId())));
    }
    List<EmailJPA> emailJPAList = personJPA.getOtherEmails();
    EmailList otherEmailsList = new EmailList();
    for (EmailJPA emailJPA : emailJPAList) {
        otherEmailsList.add(new Email(emailJPA.getEmail()));
    }
    return new PersonJpaToDomainDTO(personName, birthDate, vat, address, telephoneNumbersList, mainEmail, familyID, ledgerID, otherEmailsList, accountIDList);
}


public PersonJPA toData(Person person){
    String mainEmail = person.getMainEmail().toString();
    String name = person.getName().toString();
    String vat = person.getVat().toString();
    String birthDate = person.getBirthDate().toString();
    String familyID = person.getFamilyID().toString();
    String ledgerID = person.getLedgerID().toString();
    PersonJPA personJPA = new PersonJPA(mainEmail, name, vat, birthDate, familyID, ledgerID);
    AddressJPA address = AddressDomainAssembler.toData(person.getAddress(), personJPA);
    personJPA.setAddress(address);
    List<String> telephoneNumberList = person.getTelephoneNumbers().toStringList();
    for (String telephone : telephoneNumberList) {
        personJPA.addTelephoneNumber(new TelephoneNumberJPA(telephone, personJPA));
    }
    List<Email> otherEmails = person.getOtherEmails().getEmailList();
    List<EmailJPA> newList = new ArrayList<>();
    for (Email email : otherEmails) {
        newList.add(new EmailJPA(email.toString(), personJPA));
    }
    personJPA.setOtherEmails(newList);
    List<String> accountIDList = person.getAccountIDList().idToString();
    for (String accountID : accountIDList) {
        personJPA.addAccount(new AccountIDJPA(accountID, personJPA));
    }
    return personJPA;
}


}