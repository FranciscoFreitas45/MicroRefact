package switchtwentytwenty.project.domain.aggregate.category;
 import lombok.Getter;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import java.util.Objects;
import switchtwentytwenty.project.Interface.FamilyID;
public class Custom extends BaseCategory{

@Getter
 private  FamilyID familyID;

// Constructor Method
/**
 * Constructor of a category.
 *
 * @param categoryDesignation of the category
 */
protected Custom(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID, FamilyID familyID) {
    super(categoryDesignation, categoryID, parentID);
    this.familyID = familyID;
}
@Override
public boolean isStandard(){
    return false;
}


@Override
public int hashCode(){
    return Objects.hash(super.hashCode(), familyID);
}


@Override
public boolean equals(Object o){
    if (!super.equals(o))
        return false;
    Custom custom = (Custom) o;
    return Objects.equals(familyID, custom.familyID);
}


@Override
public boolean belongsToFamily(FamilyID familyID){
    return this.familyID.equals(familyID);
}


@Override
public boolean sameValueAs(Category other){
    if (other.isStandard()) {
        return false;
    }
    return super.hasSameValueAs(other) && this.familyID.equals(other.getFamilyID());
}


}