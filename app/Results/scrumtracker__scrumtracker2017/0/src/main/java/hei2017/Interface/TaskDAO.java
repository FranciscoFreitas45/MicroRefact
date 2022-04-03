package hei2017.Interface;
public interface TaskDAO {

   public Object findAll(Object Object);
   public Set<Task> findByTaskStoryId(Long idStory);
   public Object findOne(Object Object);
   public Task findOneById(Long id);
   public Task findOneByNom(String nom);
   public long count();
   public Object delete(Object Object);
   public Object exists(Object Object);
   public Object save(Object Object);
}