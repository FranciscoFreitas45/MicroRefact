package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICommonDaoController {

 private ICommonDao icommondao;


@GetMapping
("/update")
public int update(@RequestParam(name = "sql") String sql,@RequestParam(name = "paramMap") Map<String,?> paramMap){
  return icommondao.update(sql,paramMap);
}


@GetMapping
("/queryForObject")
public T queryForObject(@RequestParam(name = "sql") String sql,@RequestParam(name = "clazz") Class<T> clazz){
  return icommondao.queryForObject(sql,clazz);
}


@GetMapping
("/queryForInt")
public int queryForInt(@RequestParam(name = "sql") String sql,@RequestParam(name = "paramMap") Map<String,?> paramMap){
  return icommondao.queryForInt(sql,paramMap);
}


@GetMapping
("/queryForList")
public List<T> queryForList(@RequestParam(name = "selectSql") String selectSql,@RequestParam(name = "paramMap") Map<String,?> paramMap,@RequestParam(name = "page") Page page,@RequestParam(name = "clazz") Class<T> clazz){
  return icommondao.queryForList(selectSql,paramMap,page,clazz);
}


@GetMapping
("/getLastId")
public int getLastId(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "idColume") String idColume){
  return icommondao.getLastId(tableName,idColume);
}


@GetMapping
("/queryForMap")
public Map<String,Object> queryForMap(@RequestParam(name = "sql") String sql,@RequestParam(name = "paramMap") Map<String,?> paramMap){
  return icommondao.queryForMap(sql,paramMap);
}


@GetMapping
("/batchUpdate")
public int[] batchUpdate(@RequestParam(name = "sql") String sql,@RequestParam(name = "paramListMap") List<Map<String,?>> paramListMap){
  return icommondao.batchUpdate(sql,paramListMap);
}


}