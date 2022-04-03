package switchtwentytwenty.project.applicationservice.irepository;
 import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import java.util.List;
public interface ICategoryRepository {


public List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID)
;

public List<Category> getStandardCategories()
;

public boolean existsByID(CategoryID categoryID)
;

@Transactional
public Category findByID(CategoryID id)
;

public Category save(Category category)
;

public void deleteAll()
;

public List<Category> getListOfFamilyCategories(FamilyID familyID)
;

public boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation)
;

public List<Category> getListOfCategoriesWithSameParent(CategoryID parentID)
;

public boolean containsRootDesignation(Designation designation)
;

}