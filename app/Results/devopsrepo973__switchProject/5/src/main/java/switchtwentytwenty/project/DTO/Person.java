package switchtwentytwenty.project.DTO;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import java.util.Objects;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.LedgerID;
public class Person implements AggregateRoot<Person, Email>{

 private  Email mainEmail;

 private  EmailList otherEmails;

 private  AccountIDList accountIDList;

 private  PersonName name;

 private  VAT vat;

 private  BirthDate birthDate;

 private  TelephoneNumberList telephoneNumbers;

 private  Address address;

 private  FamilyID familyID;

 private  LedgerID ledgerID;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructor Methods
/**
 * Constructor Method.
 */
protected Person(Email mainEmail) {
    this.mainEmail = mainEmail;
    this.otherEmails = new EmailList();
    this.accountIDList = new AccountIDList();
    this.telephoneNumbers = new TelephoneNumberList();
}
public UserProfileOutDTO getProfile(){
    return new UserProfileOutDTO.UserProfileDTOBuilder().withName(this.name.toString()).withVat(this.vat.toString()).withBirthDate(this.birthDate.toString()).withHouseNumber(this.address.getHouseNumber().toString()).withStreet(this.address.getStreet().toString()).withCity(this.address.getCity().toString()).withCountry(this.address.getCountry().toString()).withZipCode(this.address.getZipCode().toString()).withTelephoneNumbers(this.telephoneNumbers.toStringList()).withMainEmail(this.mainEmail.toString()).withOtherEmails(this.otherEmails.toStringList()).build();
}


@Override
public Email getID(){
    return this.mainEmail;
}


public boolean isMyAccount(AccountID accountID){
    return this.accountIDList.contains(accountID);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isMyAccount"))

.queryParam("accountID",accountID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}