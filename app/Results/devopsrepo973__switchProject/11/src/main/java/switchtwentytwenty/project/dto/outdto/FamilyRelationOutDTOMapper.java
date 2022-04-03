package switchtwentytwenty.project.dto.outdto;
 import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
public class FamilyRelationOutDTOMapper {

// Attributes
// Constructor Methods
/**
 * Sole constructor.
 */
private FamilyRelationOutDTOMapper() {
}
public FamilyRelationOutDTO toDTO(FamilyRelation familyRelation){
    String personID = familyRelation.getPersonID().toString();
    String kinID = familyRelation.getKinID().toString();
    String relationType = familyRelation.getRelationType().toString();
    return new FamilyRelationOutDTO(personID, kinID, relationType);
}


}