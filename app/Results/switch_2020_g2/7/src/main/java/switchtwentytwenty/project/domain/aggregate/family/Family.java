package switchtwentytwenty.project.domain.aggregate.family;
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
import switchtwentytwenty.project.DTO.RegistrationDate;
import switchtwentytwenty.project.DTO.Email;
import switchtwentytwenty.project.DTO.AccountID;
public class Family implements AggregateRoot<Family, FamilyID>{

@NonNull
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
 private  FamilyID familyID;

 private  FamilyRelationList familyRelationList;

@NonNull
@Getter
@Setter(AccessLevel.PROTECTED)
 private  RegistrationDate registrationDate;

@NonNull
@Getter
@Setter(AccessLevel.PROTECTED)
 private  FamilyName name;

@Getter
@Setter(AccessLevel.PROTECTED)
@NonNull
 private  Email administratorID;

 private  AccountID cashAccountID;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
@NonNull
 private  LedgerID ledgerID;

 private  List<FamilyRelation> familyRelations;

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
public void add(FamilyRelation familyRelation){
    boolean found = false;
    int listSize = this.familyRelations.size();
    for (int i = 0; i < listSize && !found; i++) {
        FamilyRelation relation = familyRelations.get(i);
        if (relation.samePersonsInvolved(familyRelation.getPersonID(), familyRelation.getKinID())) {
            familyRelations.remove(relation);
            found = true;
        } else if (relation.samePersonsInvolved(familyRelation.getKinID(), familyRelation.getPersonID())) {
            familyRelations.remove(relation);
            familyRelation = familyRelation.getInverse();
            found = true;
        }
    }
    this.familyRelations.add(familyRelation);
}


public List<FamilyRelation> getFamilyRelationList(){
    List<FamilyRelation> familyRelationList = new ArrayList<>();
    familyRelationList.addAll(familyRelations);
    return familyRelationList;
}


public boolean ownsCashAccount(AccountID accountID){
    return this.cashAccountID != null && cashAccountID.equals(accountID);
}


public Optional<AccountID> getCashAccountID(){
    if (this.cashAccountID != null) {
        return Optional.of(this.cashAccountID);
    }
    return Optional.empty();
}


public boolean addAccountID(AccountID newAccountID){
    if (this.cashAccountID == null) {
        this.cashAccountID = newAccountID;
        return true;
    }
    return false;
}


@Override
public FamilyID getID(){
    return this.familyID;
}


@Override
public boolean hasSameID(FamilyID familyID){
    return this.familyID.equals(familyID);
}


public boolean sameValueAs(Family other){
    return this.familyID.equals(other.familyID) && this.name.equals(other.name) && this.registrationDate.equals(other.registrationDate) && this.administratorID.equals(other.administratorID);
}


public boolean isMyAccount(AccountID accountID){
    return this.cashAccountID.equals(accountID);
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


public FamilyRelation createFamilyRelation(Email personID,Email kinID,RelationType relationType){
    FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationType);
    this.familyRelationList.add(familyRelation);
    return familyRelation;
}


public boolean isMyChild(Email parentID,Email childID){
    for (FamilyRelation familyRelation : this.familyRelations) {
        if (familyRelation.isMyChild(parentID, childID)) {
            return true;
        }
    }
    return false;
}


@Override
public int hashCode(){
    return Objects.hash(familyID);
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    Family family = (Family) other;
    return Objects.equals(familyID, family.familyID);
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


public void setFamilyRelationList(List<FamilyRelation> list){
    this.familyRelationList = new FamilyRelationList(list);
}


public boolean isAdministrator(Email personID){
    return this.administratorID.equals(personID);
}


public boolean hasCashAccount(){
    return cashAccountID != null;
}


}