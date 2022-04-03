package hei2017.Interface;
public interface TaskDAO {

   public Set<Task> findByTaskUsersId(Long id);
}