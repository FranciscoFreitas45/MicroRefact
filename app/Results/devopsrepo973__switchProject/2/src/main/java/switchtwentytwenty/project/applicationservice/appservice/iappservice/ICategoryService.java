package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.dto.outdto.CategoryTreeOutDTO;
import switchtwentytwenty.project.dto.outdto.CustomCategoryOutDTO;
import switchtwentytwenty.project.dto.outdto.StandardCategoryOutDTO;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.exception;
import java.util.List;
public interface ICategoryService {


public StandardCategoryOutDTO createChildStandardCategory(String designation,String parentID)
;

public List<StandardCategoryOutDTO> getListOfStandardCategories()
;

public CategoryOutDTO getCategoryByID(String categoryID)
;

public List<StandardCategoryOutDTO> getListOfAllStandardCategories()
;

public StandardCategoryOutDTO createRootStandardCategory(String designation)
;

public List<CategoryTreeOutDTO> getStandardCategoriesTree()
;

public CustomCategoryOutDTO createRootCustomCategory(CustomCategoryDTO dto)
;

public List<CategoryOutDTO> getListOfFamilyCategories(String familyID)
;

public CustomCategoryOutDTO createChildCustomCategory(CustomCategoryDTO dto)
;

}