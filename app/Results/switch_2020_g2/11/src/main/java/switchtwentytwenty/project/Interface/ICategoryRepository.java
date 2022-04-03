package switchtwentytwenty.project.Interface;
public interface ICategoryRepository {

   public boolean containsRootDesignation(Designation designation);
   public Category save(Category category);
   public Category findByID(CategoryID id);
   public boolean containsDesignationWithSameParent(CategoryID parentID,Designation designation);
   public List<Category> getStandardCategories();
   public List<Category> getListOfCategoriesWithSameParent(CategoryID parentID);
   public List<Category> getListOfStandardCategoriesWithSameParent(CategoryID parentID);
   public List<Category> getListOfFamilyCategories(FamilyID familyID);
}