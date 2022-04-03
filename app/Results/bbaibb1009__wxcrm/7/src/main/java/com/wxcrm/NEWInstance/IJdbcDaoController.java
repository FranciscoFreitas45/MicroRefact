package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IJdbcDaoController {

 private IJdbcDao ijdbcdao;


@PutMapping
("/queryForPage")
public void queryForPage(@RequestParam(name = "page") Page page){
ijdbcdao.queryForPage(page);
}


@GetMapping
("/delete")
public int delete(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.delete(sql,args);
}


@GetMapping
("/queryForList")
public List<Map<String,Object>> queryForList(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.queryForList(sql,args);
}


@GetMapping
("/queryForInt")
public int queryForInt(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.queryForInt(sql,args);
}


@GetMapping
("/batchUpdate")
public int[] batchUpdate(@RequestParam(name = "sql") String sql,@RequestParam(name = "batchArgs") List<Object[]> batchArgs){
  return ijdbcdao.batchUpdate(sql,batchArgs);
}


@GetMapping
("/update")
public int update(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.update(sql,args);
}


@GetMapping
("/queryForString")
public String queryForString(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.queryForString(sql,args);
}


@GetMapping
("/add")
public int add(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.add(sql,args);
}


@GetMapping
("/queryForMap")
public Map<String,Object> queryForMap(@RequestParam(name = "sql") String sql,@RequestParam(name = "args") Object[] args){
  return ijdbcdao.queryForMap(sql,args);
}


}