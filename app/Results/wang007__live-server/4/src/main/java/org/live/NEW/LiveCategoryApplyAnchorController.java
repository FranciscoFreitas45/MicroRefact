package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.LiveCategory;
@RestController
@CrossOrigin
public class LiveCategoryApplyAnchorController {

@Autowired
 private LiveCategoryApplyAnchorService livecategoryapplyanchorservice;


@GetMapping
("/ApplyAnchor/{id}/LiveCategory/getCategory")
public LiveCategory getCategory(@PathVariable(name="id") String idHCR5){
return livecategoryapplyanchorservice.getCategory(idHCR5);
}


@PutMapping
("/ApplyAnchor/{id}/LiveCategory/setCategory")
public void setCategory(@PathVariable(name="id") String idHCR5,@RequestBody LiveCategory category){
livecategoryapplyanchorservice.setCategory(idHCR5,category);
}


}