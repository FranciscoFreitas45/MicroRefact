package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MarketController {

 private MarketRepository marketrepository;


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") String id,@RequestParam(name = "createTime") String createTime){
 marketrepository.setCreateTime(id,createTime);
}


}