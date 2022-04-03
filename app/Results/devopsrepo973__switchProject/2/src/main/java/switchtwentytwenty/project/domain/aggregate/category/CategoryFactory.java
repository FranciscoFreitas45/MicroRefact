package switchtwentytwenty.project.domain.aggregate.category;
 import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.CategoryVoDTO;
public class CategoryFactory {

/**
 * Private constructor.
 */
private CategoryFactory() {
}
public Category create(Designation designation,CategoryID id,CategoryID parentID,FamilyID familyID){
    return new Custom(designation, id, parentID, familyID);
}


}