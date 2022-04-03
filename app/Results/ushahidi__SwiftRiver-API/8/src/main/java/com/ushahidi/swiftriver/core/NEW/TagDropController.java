package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Tag;
@RestController
@CrossOrigin
public class TagDropController {

@Autowired
 private TagDropService tagdropservice;


@GetMapping
("/Drop/{id}/Tag/getTags")
public List<Tag> getTags(@PathVariable(name="id") long id){
return tagdropservice.getTags(id);
}


@PutMapping
("/Drop/{id}/Tag/setTags")
public void setTags(@PathVariable(name="id") long id,@RequestBody List<Tag> tags){
tagdropservice.setTags(id,tags);
}


}