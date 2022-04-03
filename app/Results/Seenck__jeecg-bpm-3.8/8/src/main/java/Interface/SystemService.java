package Interface;
public interface SystemService {

   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object getEntity(Object Object);
   public List<DictEntity> queryDict(String dicTable,String dicCode,String dicText);
   public Object getCountForJdbcParam(Object Object);
   public Object getSession(Object Object);
   public Object executeSql(Object Object);
   public Object findByProperty(Object Object);
   public Object findHql(Object Object);
   public Object batchSave(Object Object);
   public Object get(Object Object);
}