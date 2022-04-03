package switchtwentytwenty.project.interfaceadaptor.repository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.datamodel.CategoryJPA;
import switchtwentytwenty.project.datamodel.assembler.CategoryDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.todomaindto.CategoryVoDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.CategoryJPARepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import switchtwentytwenty.project.DTO.FamilyID;
@Repository
public class CategoryRepository implements ICategoryRepository{

@Autowired
 private CategoryJPARepository repository;

@Autowired
 private CategoryDomainDataAssembler assembler;


public List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID){
    Iterable<CategoryJPA> iterable = repository.findCategoryJPAStandardByParentID(parentID.toString());
    List<Category> categories = new ArrayList<>();
    for (CategoryJPA categoryJPA : iterable) {
        CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
        Category category = CategoryFactory.create(categoryVoDTO);
        categories.add(category);
    }
    return categories;
}


public boolean existsByID(CategoryID categoryID){
    return this.repository.existsById(categoryID.toString());
}


@Transactional
public List<Category> getStandardCategories(){
    Iterable<CategoryJPA> categories = repository.findCategoryJPAStandard();
    List<Category> standardCategories = new ArrayList<>();
    for (CategoryJPA categoryJPA : categories) {
        CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
        Category category = CategoryFactory.create(categoryVoDTO);
        standardCategories.add(category);
    }
    return standardCategories;
}


@Transactional
public Category findByID(CategoryID id){
    Optional<CategoryJPA> optionalCategoryJPA = this.repository.findById(id.toString());
    if (optionalCategoryJPA.isPresent()) {
        CategoryJPA categoryJPA = optionalCategoryJPA.get();
        CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
        return CategoryFactory.create(categoryVoDTO);
    }
    throw new ElementNotFoundException("Category not found");
}


public Category save(Category category){
    CategoryJPA categoryJPA = assembler.toData(category);
    CategoryJPA responseCategoryJPA = this.repository.save(categoryJPA);
    CategoryVoDTO responseCategoryVoDTO = assembler.toDomain(responseCategoryJPA);
    return CategoryFactory.create(responseCategoryVoDTO);
}


@Override
public void deleteAll(){
    this.repository.deleteAll();
}


@Transactional
public List<Category> getListOfFamilyCategories(FamilyID familyID){
    Iterable<CategoryJPA> iterable = repository.findByFamilyID(familyID.toString());
    List<Category> categoryList = new ArrayList<>();
    for (CategoryJPA categoryJPA : iterable) {
        CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
        Category category = CategoryFactory.create(categoryVoDTO);
        categoryList.add(category);
    }
    return categoryList;
}


@Transactional
public boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation){
    Iterable<CategoryJPA> categories = repository.findCategoryJPAByParentIDWithDesignation(parentID.toString(), designation.toString());
    for (CategoryJPA categoryJPA : categories) {
        if (categoryJPA.getDesignation().equals(designation.toString())) {
            return true;
        }
    }
    return false;
}


public List<Category> getListOfCategoriesWithSameParent(CategoryID parentID){
    Iterable<CategoryJPA> iterable = repository.findCategoryJPAByParentID(parentID.toString());
    List<Category> categories = new ArrayList<>();
    for (CategoryJPA categoryJPA : iterable) {
        CategoryVoDTO categoryVoDTO = assembler.toDomain(categoryJPA);
        Category category = CategoryFactory.create(categoryVoDTO);
        categories.add(category);
    }
    return categories;
}


@Transactional
public boolean containsRootDesignation(Designation designation){
    Iterable<CategoryJPA> categories = repository.findCategoryJPAByDesignation(designation.toString());
    return categories.iterator().hasNext();
}


}