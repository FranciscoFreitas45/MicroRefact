package Interface;
public interface SystemService {

   public Object getDataGridReturn(Object Object);
   public Object getEntity(Object Object);
   public Object delete(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object save(Object Object);
   public Object get(Object Object);
   public Object saveOrUpdate(Object Object);
   public Object getList(Object Object);
   public Object findHql(Object Object);
   public Object findUniqueByProperty(Object Object);
}