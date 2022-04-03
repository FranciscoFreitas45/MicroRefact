package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnchorController {

 private AnchorRepository anchorrepository;


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") String id,@RequestParam(name = "createTime") Date createTime){
 anchorrepository.setCreateTime(id,createTime);
}


@PutMapping
("/setRealName/{id}")
public void setRealName(@PathVariable(name = "id") String id,@RequestParam(name = "realName") String realName){
 anchorrepository.setRealName(id,realName);
}


@PutMapping
("/setIdCard/{id}")
public void setIdCard(@PathVariable(name = "id") String id,@RequestParam(name = "idCard") String idCard){
 anchorrepository.setIdCard(id,idCard);
}


@PutMapping
("/setDescription/{id}")
public void setDescription(@PathVariable(name = "id") String id,@RequestParam(name = "description") String description){
 anchorrepository.setDescription(id,description);
}


}