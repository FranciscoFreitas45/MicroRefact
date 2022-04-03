package Interface;
public interface CallOutFilterRepository {

   public Object save(Object Object);
   public Page<CallOutFilter> findAll(Specification<CallOutFilter> spec,Pageable pageable);
}