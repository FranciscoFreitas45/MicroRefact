package sn.Interface;
public interface PostRepository {

   public Object findById(Object Object);
   public List<Post> findAllByPersonId(long personId,Pageable pageable);
   public Object saveAndFlush(Object Object);
   public int getTotalCountPostsByPersonId(long personId);
   public List<Post> findAllByTextAndTime(String text,LocalDateTime dateFrom,LocalDateTime dateTo,Pageable pageable);
   public Object getOne(Object Object);
}