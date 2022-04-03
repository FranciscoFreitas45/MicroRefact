package Interface;
public interface JdbcDao {

   public List<Map<String,Object>> findForListMap(String sql,Map parameters,int page,int rows);
   public long findForLong(String sql,Map parameters);
   public List<Map<String,Object>> findForJdbc(String sql,Object objs);
}