package Interface;
public interface CallOutTaskRepository {

   public Page<CallOutTask> findAll(Specification<CallOutTask> spec,Pageable pageable);
   public Object save(Object Object);
}