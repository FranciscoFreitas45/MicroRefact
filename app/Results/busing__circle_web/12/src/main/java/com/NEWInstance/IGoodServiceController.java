package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IGoodServiceController {

 private IGoodService igoodservice;


@GetMapping
("/queryCurrentBatchGoodList")
public List<Map<String,Object>> queryCurrentBatchGoodList(@RequestParam(name = "page") Page page,@RequestParam(name = "batchId") int batchId){
  return igoodservice.queryCurrentBatchGoodList(page,batchId);
}


@GetMapping
("/queryGood")
public Map<String,Object> queryGood(@RequestParam(name = "id") String id){
  return igoodservice.queryGood(id);
}


}