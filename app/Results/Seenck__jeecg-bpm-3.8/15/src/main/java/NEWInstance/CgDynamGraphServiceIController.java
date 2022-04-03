package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgDynamGraphServiceIController {

 private CgDynamGraphServiceI cgdynamgraphservicei;


@GetMapping
("/queryCgDynamGraphConfig")
public Map<String,Object> queryCgDynamGraphConfig(@RequestParam(name = "reportId") String reportId){
  return cgdynamgraphservicei.queryCgDynamGraphConfig(reportId);
}


@GetMapping
("/queryByCgDynamGraphSql")
public List<Map<String,Object>> queryByCgDynamGraphSql(@RequestParam(name = "sql") String sql,@RequestParam(name = "params") Map params,@RequestParam(name = "paramData") Map<String,Object> paramData){
  return cgdynamgraphservicei.queryByCgDynamGraphSql(sql,params,paramData);
}


@GetMapping
("/countQueryByCgDynamGraphSql")
public long countQueryByCgDynamGraphSql(@RequestParam(name = "sql") String sql,@RequestParam(name = "params") Map params,@RequestParam(name = "paramData") Map<String,Object> paramData){
  return cgdynamgraphservicei.countQueryByCgDynamGraphSql(sql,params,paramData);
}


}