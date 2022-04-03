package Interface;
public interface SysDicRepository {

   public SysDic findByCode(String code);
   public Page<SysDic> findByDicid(String id,Pageable paramPageable);
   public List<SysDic> findByParentid(String type);
}