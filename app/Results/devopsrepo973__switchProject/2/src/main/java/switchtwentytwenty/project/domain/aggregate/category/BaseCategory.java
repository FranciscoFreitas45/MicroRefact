package switchtwentytwenty.project.domain.aggregate.category;
 import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import java.util.Objects;
public class BaseCategory implements Category{

 protected  CategoryID id;

 protected  Designation categoryDesignation;

 protected  CategoryID parentID;

// Constructor Methods
/**
 * Constructor of a base category.
 *
 * @param categoryDesignation - designation
 */
protected BaseCategory(Designation categoryDesignation, CategoryID categoryID, CategoryID parentID) {
    this.parentID = parentID;
    this.categoryDesignation = categoryDesignation;
    this.id = categoryID;
}
public Designation getDesignation(){
    return this.categoryDesignation;
}


public boolean hasSameValueAs(Category other){
    if (this.parentID != null && other.getParentID() != null) {
        return this.parentID.equals(other.getParentID()) && this.id.equals(other.getID()) && (other.getDesignation().equals(getDesignation()));
    } else if (this.parentID == null && other.getParentID() == null) {
        return this.id.equals(other.getID()) && (other.getDesignation().equals(getDesignation()));
    }
    return false;
}


@Override
public int hashCode(){
    return Objects.hash(id, categoryDesignation);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    BaseCategory that = (BaseCategory) o;
    return Objects.equals(id, that.id);
}


public CategoryID getID(){
    return this.id;
}


public boolean hasSameDesignation(Designation categoryDesignation){
    return this.categoryDesignation.equals(categoryDesignation);
}


public boolean hasSameID(CategoryID categoryID){
    if (categoryID == null) {
        throw new NullPointerException("Category ID is null");
    }
    return this.id.equals(categoryID);
}


public CategoryID getParentID(){
    return this.parentID;
}


}