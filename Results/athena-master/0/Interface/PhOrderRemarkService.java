public interface PhOrderRemarkService {

   public List<PhOrderRemark> save(List<PhOrderRemark> entities);
   public Page<PhOrderRemark> findAllByPage(String id,Pageable page);
   public void delete(Long id);
}