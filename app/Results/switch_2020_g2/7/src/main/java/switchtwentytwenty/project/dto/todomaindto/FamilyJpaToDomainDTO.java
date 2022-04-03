package switchtwentytwenty.project.dto.todomaindto;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import java.util.List;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.RegistrationDate;
import switchtwentytwenty.project.Interface.AccountID;
@NoArgsConstructor
public class FamilyJpaToDomainDTO {

@Getter
@Setter
 private  FamilyName name;

@Getter
@Setter
 private  FamilyID familyID;

@Getter
@Setter
 private  LedgerID ledgerID;

@Getter
@Setter
 private  Email administratorID;

@Getter
@Setter
 private  RegistrationDate registrationDate;

@Getter
@Setter
 private  AccountID accountID;

@Getter
@Setter
 private  List<FamilyRelation> familyRelation;


}