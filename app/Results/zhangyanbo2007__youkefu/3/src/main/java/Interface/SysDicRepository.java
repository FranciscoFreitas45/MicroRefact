package Interface;
public interface SysDicRepository {

   public List<SysDic> findByParentid(String type);
}