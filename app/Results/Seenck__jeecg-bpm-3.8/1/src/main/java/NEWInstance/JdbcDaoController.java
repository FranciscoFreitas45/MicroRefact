package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JdbcDaoController {

 private JdbcDao jdbcdao;


@GetMapping
("/batchUpdate")
public int[] batchUpdate(@RequestParam(name = "sql") String sql,@RequestParam(name = "batch") List<Object[]> batch){
  return jdbcdao.batchUpdate(sql,batch);
}


@GetMapping
("/execute")
public Object execute(@RequestParam(name = "Object") Object Object){
  return jdbcdao.execute(Object);
}


@GetMapping
("/findForJdbc")
public List<Map<String,Object>> findForJdbc(@RequestParam(name = "sql") String sql,@RequestParam(name = "objs") Object objs){
  return jdbcdao.findForJdbc(sql,objs);
}


@GetMapping
("/findForListMap")
public List<Map<String,Object>> findForListMap(@RequestParam(name = "sql") String sql,@RequestParam(name = "parameters") Map parameters,@RequestParam(name = "page") int page,@RequestParam(name = "rows") int rows){
  return jdbcdao.findForListMap(sql,parameters,page,rows);
}


@GetMapping
("/findForLong")
public long findForLong(@RequestParam(name = "sql") String sql,@RequestParam(name = "parameters") Map parameters){
  return jdbcdao.findForLong(sql,parameters);
}


}