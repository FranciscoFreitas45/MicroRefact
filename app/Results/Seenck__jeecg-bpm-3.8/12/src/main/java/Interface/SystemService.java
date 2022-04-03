package Interface;
public interface SystemService {

   public Object getEntity(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object saveOrUpdate(Object Object);
   public Object get(Object Object);
   public Object getDataGridReturn(Object Object);
   public Object delete(Object Object);
}