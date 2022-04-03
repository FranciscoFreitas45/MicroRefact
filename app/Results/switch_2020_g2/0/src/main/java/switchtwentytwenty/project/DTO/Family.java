package switchtwentytwenty.project.DTO;
 import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import switchtwentytwenty.project.Interface.RegistrationDate;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.LedgerID;
public class Family implements AggregateRoot<Family, FamilyID>{

 private  FamilyID familyID;

 private  FamilyRelationList familyRelationList;

 private  RegistrationDate registrationDate;

 private  FamilyName name;

 private  Email administratorID;

 private  AccountID cashAccountID;

 private  LedgerID ledgerID;

 private  List<FamilyRelation> familyRelations;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

// Constructor Methods
/**
 * Sole constructor
 *
 * @param familyID - family familyID
 */
protected Family(FamilyID familyID) {
    Objects.requireNonNull(familyID);
    this.familyID = familyID;
    this.registrationDate = new RegistrationDate();
    this.familyRelationList = new FamilyRelationList();
}
public List<FamilyRelation> getFamilyRelationList(){
    List<FamilyRelation> familyRelationList = new ArrayList<>();
    familyRelationList.addAll(familyRelations);
    return familyRelationList;
}


public Optional<AccountID> getCashAccountID(){
    if (this.cashAccountID != null) {
        return Optional.of(this.cashAccountID);
    }
    return Optional.empty();
}


@Override
public FamilyID getID(){
    return this.familyID;
}


public List<FamilyRelation> getFamilyRelationByPersonID(Email personID){
    return this.familyRelationList.getFamilyRelationListByID(personID);
}


public Optional<FamilyRelation> getFamilyRelationByIDs(Email personID,Email kinID){
    for (FamilyRelation familyRelation : familyRelations) {
        if (familyRelation.samePersonsInvolved(personID, kinID) || familyRelation.samePersonsInvolved(kinID, personID)) {
            return Optional.of(familyRelation);
        }
    }
    return Optional.empty();
}


public List<FamilyRelation> getFamilyRelationListByID(Email personID){
    List<FamilyRelation> familyRelationList = new ArrayList<>();
    for (FamilyRelation familyRelation : familyRelations) {
        if (familyRelation.getPersonID().equals(personID) || familyRelation.getKinID().equals(personID)) {
            familyRelationList.add(familyRelation);
        }
    }
    return familyRelationList;
}


public boolean isMyChild(Email parentID,Email childID){
    for (FamilyRelation familyRelation : this.familyRelations) {
        if (familyRelation.isMyChild(parentID, childID)) {
            return true;
        }
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isMyChild"))

.queryParam("parentID",parentID)
.queryParam("childID",childID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isAdministrator(Email personID){
    return this.administratorID.equals(personID);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAdministrator"))

.queryParam("personID",personID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean ownsCashAccount(AccountID accountID){
    return this.cashAccountID != null && cashAccountID.equals(accountID);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ownsCashAccount"))

.queryParam("accountID",accountID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean hasCashAccount(){
    return cashAccountID != null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasCashAccount"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean addAccountID(AccountID newAccountID){
    if (this.cashAccountID == null) {
        this.cashAccountID = newAccountID;
        return true;
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addAccountID"))

.queryParam("newAccountID",newAccountID)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}