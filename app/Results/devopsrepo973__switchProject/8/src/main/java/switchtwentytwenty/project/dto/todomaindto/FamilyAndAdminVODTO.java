package switchtwentytwenty.project.dto.todomaindto;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address;
import switchtwentytwenty.project.Interface.PersonName;
import switchtwentytwenty.project.Interface.BirthDate;
import switchtwentytwenty.project.Interface.VAT;
import switchtwentytwenty.project.Interface.Address;
import switchtwentytwenty.project.Interface.TelephoneNumberList;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerID;
@NoArgsConstructor
public class FamilyAndAdminVODTO {

@Setter(AccessLevel.PROTECTED)
@Getter
 private  PersonName personName;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  BirthDate birthDate;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  VAT vat;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  Address address;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  TelephoneNumberList phoneNumbers;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  Email email;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  FamilyName familyName;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  FamilyID familyID;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  LedgerID familyLedgerID;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  LedgerID personLedgerID;


public FamilyAndAdminVODTO addIDs(FamilyID familyID,LedgerID familyLedgerID,LedgerID personLedgerID){
    FamilyAndAdminVODTO newDTO = new FamilyAndAdminVODTO();
    newDTO.setPersonName(this.personName);
    newDTO.setBirthDate(this.birthDate);
    newDTO.setVat(this.vat);
    newDTO.setAddress(this.address);
    newDTO.setPhoneNumbers(this.phoneNumbers);
    newDTO.setEmail(this.email);
    newDTO.setFamilyName(this.familyName);
    newDTO.setFamilyID(familyID);
    newDTO.setFamilyLedgerID(familyLedgerID);
    newDTO.setPersonLedgerID(personLedgerID);
    return newDTO;
}


}