package switchtwentytwenty.project.dto.todomaindto;
 import lombok;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.LedgerID;
@AllArgsConstructor
@NoArgsConstructor
public class PersonVoDTO {

@Getter
@Setter(AccessLevel.PROTECTED)
 private  PersonName name;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  BirthDate birthDate;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  VAT vat;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  Address address;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  TelephoneNumberList phoneNumbers;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  Email email;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  FamilyID familyID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  LedgerID ledgerID;


}