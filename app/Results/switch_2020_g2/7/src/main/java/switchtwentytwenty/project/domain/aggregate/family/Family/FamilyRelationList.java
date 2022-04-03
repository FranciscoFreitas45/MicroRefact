package switchtwentytwenty.project.domain.aggregate.family.Family;
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
public class FamilyRelationList {

 private  List<FamilyRelation> familyRelations;

// Constructor Methods
/**
 * Sole constructor
 */
private FamilyRelationList() {
    this.familyRelations = new ArrayList<>();
}/**
 * Sole constructor
 */
private FamilyRelationList(List<FamilyRelation> initialList) {
    this.familyRelations = new ArrayList<>(initialList);
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


public boolean isMyChild(Email parentID,Email childID){
    for (FamilyRelation familyRelation : this.familyRelations) {
        if (familyRelation.isMyChild(parentID, childID)) {
            return true;
        }
    }
    return false;
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


public Optional<FamilyRelation> getFamilyRelationByIDs(Email personID,Email kinID){
    for (FamilyRelation familyRelation : familyRelations) {
        if (familyRelation.samePersonsInvolved(personID, kinID) || familyRelation.samePersonsInvolved(kinID, personID)) {
            return Optional.of(familyRelation);
        }
    }
    return Optional.empty();
}


}