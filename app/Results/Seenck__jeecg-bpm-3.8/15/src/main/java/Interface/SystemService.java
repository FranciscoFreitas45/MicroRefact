package Interface;
public interface SystemService {

   public Object getEntity(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object findHql(Object Object);
   public Object findUniqueByProperty(Object Object);
}