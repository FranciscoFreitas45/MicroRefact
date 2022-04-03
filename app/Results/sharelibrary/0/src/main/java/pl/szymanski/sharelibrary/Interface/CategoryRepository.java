package pl.szymanski.sharelibrary.Interface;
public interface CategoryRepository {

   public Optional<Category> findByName(String name);
}