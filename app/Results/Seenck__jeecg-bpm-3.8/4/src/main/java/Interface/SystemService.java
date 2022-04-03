package Interface;
public interface SystemService {

   public Object findForJdbcParam(Object Object);
   public Object findForJdbc(Object Object);
   public Object getEntity(Object Object);
   public Object findHql(Object Object);
   public Object saveOrUpdate(Object Object);
   public Object executeSql(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object findByExample(Object Object);
}