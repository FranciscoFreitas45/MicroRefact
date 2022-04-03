package Interface;
public interface JeecgDictDao {

   public List<DictEntity> queryCustomDict(String dicTable,String dicCode,String dicText);
   public List<DictEntity> querySystemDict(String dicCode);
}