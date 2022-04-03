package main.Interface;
public interface TagRepository {

   public List<String> findByName(String query);
   public List<String> findNamesOfTags();
   public Object getOne(Object Object);
}