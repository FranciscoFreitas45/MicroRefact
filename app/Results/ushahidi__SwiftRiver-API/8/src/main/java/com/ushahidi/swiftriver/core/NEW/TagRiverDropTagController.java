package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.Tag;
@RestController
@CrossOrigin
public class TagRiverDropTagController {

@Autowired
 private TagRiverDropTagService tagriverdroptagservice;


@PutMapping
("/RiverDropTag/{id}/Tag/setTag")
public void setTag(@PathVariable(name="id") long id3OUD,@RequestBody Tag tag){
tagriverdroptagservice.setTag(id3OUD,tag);
}


@GetMapping
("/RiverDropTag/{id}/Tag/getTag")
public Tag getTag(@PathVariable(name="id") long id3OUD){
return tagriverdroptagservice.getTag(id3OUD);
}


}