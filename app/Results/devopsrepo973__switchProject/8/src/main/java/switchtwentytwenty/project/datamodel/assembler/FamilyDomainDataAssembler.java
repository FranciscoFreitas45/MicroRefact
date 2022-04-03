package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.todomaindto.FamilyJpaToDomainDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.util.Util;
import java.io.IOException;
import java.util;
@Service
public class FamilyDomainDataAssembler {


public List<FamilyRelation> getFamilyRelations(FamilyJPA familyJPA){
    List<FamilyRelation> familyRelationList = new ArrayList<>();
    List<FamilyRelationJPA> familyRelations = familyJPA.getFamilyRelationList();
    if (!(familyRelations.isEmpty() || familyRelations == null)) {
        for (FamilyRelationJPA jpa : familyRelations) {
            FamilyRelationIDJPA familyRelationJPAId = jpa.getId();
            String relationType = jpa.getRelationType();
            String personId = familyRelationJPAId.getPersonID();
            String kinId = familyRelationJPAId.getKinID();
            RelationType relationT = RelationType.getInstance(relationType);
            Email personID = new Email(personId);
            Email kinID = new Email(kinId);
            FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationT);
            familyRelationList.add(familyRelation);
        }
    }
    return familyRelationList;
}


public FamilyJPA toData(Family family){
    FamilyJPA familyJPA = new FamilyJPA(family.getID().toString(), family.getRegistrationDate().toString(), family.getName().toString(), family.getAdministratorID().toString(), family.getLedgerID().toString());
    String account;
    Optional<AccountID> accountID = family.getCashAccountID();
    if (accountID.isPresent()) {
        account = accountID.get().toString();
        familyJPA.setCashAccountID(account);
    }
    return familyJPA;
}


public FamilyJpaToDomainDTO toDomain(FamilyJPA familyJPA){
    FamilyID familyID = new FamilyID(UUID.fromString(familyJPA.getId()));
    FamilyName familyName = new FamilyName(familyJPA.getName());
    Email adminID = new Email(familyJPA.getAdministratorID());
    LedgerID ledgerID = new LedgerID(UUID.fromString(familyJPA.getLedgerID()));
    Date date = Util.stringToDate(familyJPA.getRegistrationDate(), Constants.REGISTRATION_DATE_FORMAT);
    RegistrationDate registrationDate = new RegistrationDate();
    registrationDate.setDate(date);
    FamilyJpaToDomainDTO dto = new FamilyJpaToDomainDTO();
    dto.setFamilyID(familyID);
    dto.setName(familyName);
    dto.setAdministratorID(adminID);
    dto.setLedgerID(ledgerID);
    dto.setRegistrationDate(registrationDate);
    String accountID = familyJPA.getCashAccountID();
    if (accountID != null) {
        AccountID accountid = new AccountID(UUID.fromString(accountID));
        dto.setAccountID(accountid);
    }
    List<FamilyRelation> familyRelationList = getFamilyRelations(familyJPA);
    dto.setFamilyRelation(familyRelationList);
    return dto;
}


}