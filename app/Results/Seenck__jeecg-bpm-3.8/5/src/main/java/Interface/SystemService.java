package Interface;
public interface SystemService {

   public Object getEntity(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object findUniqueByProperty(Object Object);
   public Object executeSql(Object Object);
   public Object save(Object Object);
   public Object findForJdbc(Object Object);
}