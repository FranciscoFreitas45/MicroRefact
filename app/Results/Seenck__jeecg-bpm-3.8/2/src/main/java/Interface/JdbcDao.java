package Interface;
public interface JdbcDao {

   public int[] batchUpdate(String sql,List<Object[]> batch);
   public Object execute(Object Object);
}