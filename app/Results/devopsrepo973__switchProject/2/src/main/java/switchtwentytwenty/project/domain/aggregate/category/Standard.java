package switchtwentytwenty.project.domain.aggregate.category;
 import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
public class Standard extends BaseCategory{

// Attributes
// Constructor Method
/**
 * Constructor of a category
 *
 * @param categoryDesignation of the category
 */
protected Standard(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID) {
    super(categoryDesignation, categoryID, parentID);
}
@Override
public boolean isStandard(){
    return true;
}


@Override
public FamilyID getFamilyID(){
    throw new IllegalArgumentException("Does not apply to Standard Categories");
}


@Override
public boolean belongsToFamily(FamilyID familyID){
    return true;
}


@Override
public boolean sameValueAs(Category other){
    if (!other.isStandard()) {
        return false;
    }
    return super.hasSameValueAs(other);
}


}