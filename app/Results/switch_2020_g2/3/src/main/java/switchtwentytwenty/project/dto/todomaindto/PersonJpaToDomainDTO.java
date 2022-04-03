package switchtwentytwenty.project.dto.todomaindto;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import switchtwentytwenty.project.domain.share.id;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.LedgerID;
@AllArgsConstructor
public class PersonJpaToDomainDTO {

@Getter
 private  PersonName name;

@Getter
 private  BirthDate birthDate;

@Getter
 private  VAT vat;

@Getter
 private  Address address;

@Getter
 private  TelephoneNumberList phoneNumbers;

@Getter
 private  Email email;

@Getter
 private  FamilyID familyID;

@Getter
 private  LedgerID ledgerID;

@Getter
 private  EmailList otherEmails;

@Getter
 private  AccountIDList accounts;


}