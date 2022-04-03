package switchtwentytwenty.project.domain.aggregate.person;
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

@Getter
 private  Email mainEmail;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  EmailList otherEmails;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  AccountIDList accountIDList;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  PersonName name;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  VAT vat;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  BirthDate birthDate;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  TelephoneNumberList telephoneNumbers;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  Address address;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  FamilyID familyID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  LedgerID ledgerID;

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
public boolean addAccountID(AccountID newAccountID){
    if (newAccountID != null) {
        this.accountIDList.add(newAccountID);
        return true;
    }
    return false;
}


public UserProfileOutDTO getProfile(){
    return new UserProfileOutDTO.UserProfileDTOBuilder().withName(this.name.toString()).withVat(this.vat.toString()).withBirthDate(this.birthDate.toString()).withHouseNumber(this.address.getHouseNumber().toString()).withStreet(this.address.getStreet().toString()).withCity(this.address.getCity().toString()).withCountry(this.address.getCountry().toString()).withZipCode(this.address.getZipCode().toString()).withTelephoneNumbers(this.telephoneNumbers.toStringList()).withMainEmail(this.mainEmail.toString()).withOtherEmails(this.otherEmails.toStringList()).build();
}


@Override
public Email getID(){
    return this.mainEmail;
}


public boolean containsEmail(Email email){
    return this.otherEmails.contains(email);
}


@Override
public boolean hasSameID(Email personID){
    if (personID == null) {
        throw new NullPointerException("Family ID is null");
    }
    return this.mainEmail.equals(personID);
}


public boolean hasSameVAT(VAT vat){
    return this.vat.equals(vat);
}


public boolean isMyAccount(AccountID accountID){
    return this.accountIDList.contains(accountID);
}


@Override
public boolean sameValueAs(Person other){
    return this.name.equals(other.name) && this.vat.equals(other.vat) && this.birthDate.equals(other.birthDate) && this.mainEmail.equals(other.mainEmail) && this.otherEmails.equals(other.otherEmails) && this.telephoneNumbers.equals(other.telephoneNumbers) && this.address.equals(other.address) && this.familyID.equals(other.familyID);
}


public boolean checkIfEmailIsRegistered(Email inputtedEmail){
    for (Email email : otherEmails.getEmailList()) {
        if (email.equals(inputtedEmail)) {
            return true;
        }
    }
    boolean repeatedEmail = false;
    if (mainEmail.equals(inputtedEmail)) {
        repeatedEmail = true;
    }
    return repeatedEmail;
}


@Override
public int hashCode(){
    return Objects.hash(mainEmail);
}


public boolean removeEmail(Email email){
    if (!containsEmail(email)) {
        throw new IllegalArgumentException("Email not found");
    }
    otherEmails.remove(email);
    return true;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    Person person = (Person) o;
    return Objects.equals(mainEmail, person.mainEmail);
}


public boolean isMemberOfFamily(FamilyID familyID){
    return this.familyID.equals(familyID);
}


public void addEmail(Email inputtedEmail){
    if (!checkIfEmailIsRegistered(inputtedEmail)) {
        otherEmails.add(inputtedEmail);
    } else {
        throw new IllegalArgumentException("Email not valid. Already exists in the app");
    }
}


}