package Interface;
public interface SystemService {

   public Object findForJdbc(Object Object);
   public Object getCountForJdbc(Object Object);
   public Object getCountForJdbcParam(Object Object);
   public Object findByQueryString(Object Object);
   public Object getEntity(Object Object);
   public void addLog(String LogContent,Short operatetype,Short loglevel);
   public Object getList(Object Object);
   public Object getDataGridReturn(Object Object);
}