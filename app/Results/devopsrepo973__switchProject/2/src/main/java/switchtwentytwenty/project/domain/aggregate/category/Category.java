package switchtwentytwenty.project.domain.aggregate.category;
 import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
public interface Category extends AggregateRoot<Category, CategoryID>{


public Designation getDesignation()
;

public boolean isStandard()
;

public CategoryID getID()
;

public boolean belongsToFamily(FamilyID familyID)
;

public boolean hasSameDesignation(Designation designation)
;

public FamilyID getFamilyID()
;

public CategoryID getParentID()
;

}