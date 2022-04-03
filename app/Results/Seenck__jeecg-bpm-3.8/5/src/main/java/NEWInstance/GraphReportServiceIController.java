package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GraphReportServiceIController {

 private GraphReportServiceI graphreportservicei;


@GetMapping
("/queryCgReportConfig")
public Map<String,Object> queryCgReportConfig(@RequestParam(name = "reportId") String reportId){
  return graphreportservicei.queryCgReportConfig(reportId);
}


@GetMapping
("/queryByCgReportSql")
public List<Map<String,Object>> queryByCgReportSql(@RequestParam(name = "sql") String sql,@RequestParam(name = "params") Map params,@RequestParam(name = "paramData") Map<String,Object> paramData,@RequestParam(name = "page") int page,@RequestParam(name = "rows") int rows){
  return graphreportservicei.queryByCgReportSql(sql,params,paramData,page,rows);
}


@GetMapping
("/countQueryByCgReportSql")
public long countQueryByCgReportSql(@RequestParam(name = "sql") String sql,@RequestParam(name = "params") Map params){
  return graphreportservicei.countQueryByCgReportSql(sql,params);
}


@GetMapping
("/getSqlFields")
public List<String> getSqlFields(@RequestParam(name = "sql") String sql){
  return graphreportservicei.getSqlFields(sql);
}


}