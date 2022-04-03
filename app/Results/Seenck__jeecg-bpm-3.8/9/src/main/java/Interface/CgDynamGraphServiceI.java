package Interface;
public interface CgDynamGraphServiceI {

   public Map<String,Object> queryCgDynamGraphConfig(String reportId);
   public List<Map<String,Object>> queryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData);
   public long countQueryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData);
}