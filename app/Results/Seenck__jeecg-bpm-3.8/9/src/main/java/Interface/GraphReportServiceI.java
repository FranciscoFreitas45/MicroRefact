package Interface;
public interface GraphReportServiceI {

   public Map<String,Object> queryCgReportConfig(String reportId);
   public List<Map<String,Object>> queryByCgReportSql(String sql,Map params,Map<String,Object> paramData,int page,int rows);
   public long countQueryByCgReportSql(String sql,Map params);
   public List<String> getSqlFields(String sql);
}