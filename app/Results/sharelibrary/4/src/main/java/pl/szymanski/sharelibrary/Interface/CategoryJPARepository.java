package pl.szymanski.sharelibrary.Interface;
public interface CategoryJPARepository {

   public Object findAll(Object Object);
   public Optional<Category> findFirstByNameIgnoreCase(String name);
}