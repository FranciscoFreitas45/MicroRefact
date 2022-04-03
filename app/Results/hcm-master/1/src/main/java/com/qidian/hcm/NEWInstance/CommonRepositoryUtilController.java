package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommonRepositoryUtilController {

 private CommonRepositoryUtil commonrepositoryutil;


@GetMapping
("/pageByNative")
public Page<T> pageByNative(@RequestParam(name = "clazz") Class clazz,@RequestParam(name = "querySql") String querySql,@RequestParam(name = "countSql") String countSql,@RequestParam(name = "params") Map<String,Object> params,@RequestParam(name = "pageNo") int pageNo,@RequestParam(name = "pageSize") int pageSize){
  return commonrepositoryutil.pageByNative(clazz,querySql,countSql,params,pageNo,pageSize);
}


}