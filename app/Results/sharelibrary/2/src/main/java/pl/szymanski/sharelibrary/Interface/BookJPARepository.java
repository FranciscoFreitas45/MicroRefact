package pl.szymanski.sharelibrary.Interface;
public interface BookJPARepository {

   public Object findAll(Object Object);
   public Object saveAndFlush(Object Object);
   public Object findById(Object Object);
   public List<Book> findByTitleIsContainingIgnoreCase(String title);
   public List<Book> findByUserId(Long userId);
}