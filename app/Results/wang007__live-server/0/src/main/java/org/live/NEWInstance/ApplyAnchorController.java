package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ApplyAnchorController {

 private ApplyAnchorRepository applyanchorrepository;


@PutMapping
("/setRealName/{id}")
public void setRealName(@PathVariable(name = "id") String id,@RequestParam(name = "realName") String realName){
 applyanchorrepository.setRealName(id,realName);
}


@PutMapping
("/setIdCard/{id}")
public void setIdCard(@PathVariable(name = "id") String id,@RequestParam(name = "idCard") String idCard){
 applyanchorrepository.setIdCard(id,idCard);
}


@PutMapping
("/setCreateTime/{id}")
public void setCreateTime(@PathVariable(name = "id") String id,@RequestParam(name = "createTime") Date createTime){
 applyanchorrepository.setCreateTime(id,createTime);
}


@PutMapping
("/setCategory/{id}")
public void setCategory(@PathVariable(name = "id") String id,@RequestParam(name = "category") LiveCategory category){
 applyanchorrepository.setCategory(id,category);
}


@PutMapping
("/setIdImgUrl/{id}")
public void setIdImgUrl(@PathVariable(name = "id") String id,@RequestParam(name = "idImgUrl") String idImgUrl){
 applyanchorrepository.setIdImgUrl(id,idImgUrl);
}


}