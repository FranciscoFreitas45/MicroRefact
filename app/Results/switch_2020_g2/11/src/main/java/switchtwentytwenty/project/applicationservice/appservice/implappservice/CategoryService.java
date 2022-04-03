package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.ApplicationContextProvider;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto;
import switchtwentytwenty.project.dto.toservicedto.CustomCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.exception.DesignationNotPossibleException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.IExternalCategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import switchtwentytwenty.project.Interface.ICategoryRepository;
import switchtwentytwenty.project.Interface.ICategoryIDGenerator;
@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{

@Autowired
 private  ICategoryRepository categoryRepository;

@Autowired
 private  ICategoryIDGenerator categoryIDGenerator;

@Autowired
 private  ApplicationContextProvider applicationContext;


public StandardCategoryOutDTO createChildStandardCategory(String designation,String parentID){
    Designation parseCategoryDesignation = new CategoryDesignation(designation);
    CategoryID parentCategoryID = new CategoryID(parentID);
    Category parentCategory = categoryRepository.findByID(parentCategoryID);
    if (parentCategory.isStandard() && categoryRepository.containsDesignationWithSameParent(parentCategoryID, parseCategoryDesignation)) {
        throw new DesignationNotPossibleException();
    }
    CategoryID categoryID = categoryIDGenerator.generate();
    Category childCategory = CategoryFactory.create(parseCategoryDesignation, categoryID, parentCategoryID);
    Category responseChildCategory = categoryRepository.save(childCategory);
    return new StandardCategoryOutDTO(responseChildCategory.getDesignation().toString(), responseChildCategory.getID().toString(), responseChildCategory.getParentID().toString());
}


public List<StandardCategoryOutDTO> getListOfStandardCategories(){
    List<Category> standardCategories = categoryRepository.getStandardCategories();
    List<StandardCategoryOutDTO> standardCategoryOutDTOS = new ArrayList<>();
    for (Category standardCategory : standardCategories) {
        StandardCategoryOutDTO standardCategoryOutDTO = StandardCategoryOutDTOMapper.toDTO(standardCategory);
        standardCategoryOutDTOS.add(standardCategoryOutDTO);
    }
    return standardCategoryOutDTOS;
}


public CategoryOutDTO getCategoryByID(String categoryID){
    CategoryID categoryIdentifier = new CategoryID(categoryID);
    Category category = categoryRepository.findByID(categoryIdentifier);
    return CategoryOutDTOMapper.toDTO(category);
}


public CategoryTreeOutDTO constructTree(CategoryTreeOutDTO tree,Category baseCategory,boolean onlyStandard){
    CategoryID baseCategoryID = baseCategory.getID();
    List<Category> categoriesWithSameParent;
    if (onlyStandard) {
        categoriesWithSameParent = categoryRepository.getListOfStandardCategoriesWithSameParent(baseCategoryID);
    } else {
        categoriesWithSameParent = categoryRepository.getListOfCategoriesWithSameParent(baseCategoryID);
    }
    for (Category category : categoriesWithSameParent) {
        CategoryTreeOutDTO childTree = new CategoryTreeOutDTO(category.getDesignation().toString());
        constructTree(childTree, category, onlyStandard);
        tree.addChildTree(childTree);
    }
    return tree;
}


public List<StandardCategoryOutDTO> getListOfAllStandardCategories(){
    List<StandardCategoryOutDTO> standardCategoryOutDTOS = new ArrayList<>();
    standardCategoryOutDTOS.addAll(getListOfStandardCategories());
    standardCategoryOutDTOS.addAll(getListOfExternalStandardCategories());
    return standardCategoryOutDTOS;
}


public List<StandardCategoryOutDTO> getListOfExternalStandardCategories(){
    IExternalCategoryRepository httpRepository = (IExternalCategoryRepository) applicationContext.getApplicationContext().getBean("groupRepo");
    List<StandardCategoryDTO> allExternalCategories = httpRepository.getStandardCategories();
    return StandardCategoryOutDTOMapper.toOutStandardCategoryDTOList(allExternalCategories);
}


public StandardCategoryOutDTO createRootStandardCategory(String designation){
    Designation parseCategoryDesignation = new CategoryDesignation(designation);
    if (categoryRepository.containsRootDesignation(parseCategoryDesignation)) {
        throw new DesignationNotPossibleException();
    }
    CategoryID categoryID = categoryIDGenerator.generate();
    Category rootCategory = CategoryFactory.create(parseCategoryDesignation, categoryID, null);
    Category responseRootCategory = categoryRepository.save(rootCategory);
    return new StandardCategoryOutDTO(responseRootCategory.getDesignation().toString(), responseRootCategory.getID().toString(), null);
}


public List<CategoryTreeOutDTO> getStandardCategoriesTree(){
    List<Category> standardCategories = categoryRepository.getStandardCategories();
    List<CategoryTreeOutDTO> trees = new ArrayList<>();
    for (Category category : standardCategories) {
        if (category.getParentID() == null) {
            CategoryTreeOutDTO rootTree = new CategoryTreeOutDTO(category.getDesignation().toString());
            constructTree(rootTree, category, true);
            trees.add(rootTree);
        }
    }
    return trees;
}


public CustomCategoryOutDTO createRootCustomCategory(CustomCategoryDTO dto){
    FamilyID familyId = new FamilyID(UUID.fromString(dto.getFamilyID()));
    Designation parseDesignation = new CategoryDesignation(dto.getDesignation());
    if (categoryRepository.containsRootDesignation(parseDesignation)) {
        throw new DesignationNotPossibleException();
    }
    CategoryID categoryID = categoryIDGenerator.generate();
    Category rootCustomCategory = CategoryFactory.create(parseDesignation, categoryID, null, familyId);
    Category responseRootCustom = categoryRepository.save(rootCustomCategory);
    return new CustomCategoryOutDTO(responseRootCustom.getDesignation().toString());
}


public List<CategoryOutDTO> getListOfFamilyCategories(String familyID){
    FamilyID parsedFamilyID = new FamilyID(UUID.fromString(familyID));
    // get internal standard and custom categories linked to this family.
    List<Category> customCategoryList = categoryRepository.getListOfFamilyCategories(parsedFamilyID);
    List<CategoryOutDTO> categoryOutDTOList = CategoryOutDTOMapper.toDTOList(customCategoryList);
    // get external standard categories
    List<StandardCategoryOutDTO> standardCategoryOutDTOList = getListOfExternalStandardCategories();
    // add external standard categories to the internal and custom categories.
    categoryOutDTOList.addAll(StandardCategoryOutDTOMapper.toOutCategoryDTOList(standardCategoryOutDTOList));
    return categoryOutDTOList;
}


public CustomCategoryOutDTO createChildCustomCategory(CustomCategoryDTO dto){
    FamilyID categoryFamilyID = new FamilyID(UUID.fromString(dto.getFamilyID()));
    CategoryID parentCategoryID = new CategoryID(dto.getParentID());
    Designation parseDesignation = new CategoryDesignation(dto.getDesignation());
    CategoryID categoryID = categoryIDGenerator.generate();
    Category childCustomCategory = CategoryFactory.create(parseDesignation, categoryID, parentCategoryID, categoryFamilyID);
    Category responseChildCustom = categoryRepository.save(childCustomCategory);
    return new CustomCategoryOutDTO(responseChildCustom.getDesignation().toString());
}


}