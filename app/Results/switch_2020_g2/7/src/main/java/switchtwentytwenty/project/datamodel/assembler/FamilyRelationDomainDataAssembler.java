package switchtwentytwenty.project.datamodel.assembler;
 import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationJPA;
import switchtwentytwenty.project.datamodel.FamilyRelationIDJPA;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import java.io.IOException;
@Service
public class FamilyRelationDomainDataAssembler {


public FamilyRelationJPA toData(FamilyRelation familyRelation,FamilyJPA familyJPA){
    return new FamilyRelationJPA(new FamilyRelationIDJPA(familyRelation.getPersonID().toString(), familyRelation.getKinID().toString()), familyRelation.getRelationType().toString(), familyJPA);
}


public FamilyRelation toDomain(FamilyRelationJPA familyRelationJPA){
    FamilyRelationIDJPA familyRelationJPAId = familyRelationJPA.getId();
    String relationType = familyRelationJPA.getRelationType();
    String personId = familyRelationJPAId.getPersonID();
    String kinId = familyRelationJPAId.getKinID();
    RelationType relationT = RelationType.getInstance(relationType);
    Email personID = new Email(personId);
    Email kinID = new Email(kinId);
    return new FamilyRelation(personID, kinID, relationT);
}


}