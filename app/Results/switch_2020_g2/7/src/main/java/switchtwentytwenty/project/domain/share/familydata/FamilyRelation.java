package switchtwentytwenty.project.domain.share.familydata;
 import lombok.Getter;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.Email;
public class FamilyRelation implements ValueObject{

@Getter
 private  Email personID;

@Getter
 private  Email kinID;

@Getter
 private  RelationType relationType;

// Constructor Methods
/**
 * Sole Constructor
 * @param personID - person identifier
 * @param kinID - kin identifier
 * @param relationType - type of relation
 */
public FamilyRelation(Email personID, Email kinID, RelationType relationType) {
    validateParameters(personID, kinID, relationType);
    this.personID = personID;
    this.kinID = kinID;
    this.relationType = relationType;
}
public boolean samePersonsInvolved(Email personID,Email kinID){
    return this.personID.equals(personID) && this.kinID.equals(kinID);
}


public FamilyRelation getInverse(){
    return new FamilyRelation(this.kinID, this.personID, this.relationType.getInverse());
}


public boolean isMyChild(Email parentID,Email childID){
    if (parentID == null || childID == null) {
        return false;
    }
    if (this.relationType.isAChildRelation() && parentID.equals(this.kinID) && childID.equals(this.personID)) {
        return true;
    }
    return this.relationType.isAParentRelation() && parentID.equals(this.personID) && childID.equals(this.kinID);
}


public void validateParameters(Email personID,Email kinID,RelationType relationType){
    if (personID == null) {
        throw new NullPointerException("Person ID is null");
    }
    if (kinID == null) {
        throw new NullPointerException("Kin ID is null");
    }
    if (relationType == null) {
        throw new NullPointerException("Relation type is null");
    }
}


}