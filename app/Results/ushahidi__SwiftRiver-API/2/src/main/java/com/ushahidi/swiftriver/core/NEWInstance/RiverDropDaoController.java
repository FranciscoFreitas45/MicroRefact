package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverDropDaoController {

 private RiverDropDao riverdropdao;


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return riverdropdao.delete(Object);
}


@GetMapping
("/findTag")
public RiverDropTag findTag(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "tag") Tag tag){
  return riverdropdao.findTag(riverDrop,tag);
}


@PutMapping
("/addTag")
public void addTag(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "tag") Tag tag){
riverdropdao.addTag(riverDrop,tag);
}


@GetMapping
("/deleteTag")
public boolean deleteTag(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "tag") Tag tag){
  return riverdropdao.deleteTag(riverDrop,tag);
}


@GetMapping
("/findLink")
public RiverDropLink findLink(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "link") Link link){
  return riverdropdao.findLink(riverDrop,link);
}


@PutMapping
("/addLink")
public void addLink(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "link") Link link){
riverdropdao.addLink(riverDrop,link);
}


@GetMapping
("/deleteLink")
public boolean deleteLink(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "link") Link link){
  return riverdropdao.deleteLink(riverDrop,link);
}


@GetMapping
("/findPlace")
public RiverDropPlace findPlace(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "place") Place place){
  return riverdropdao.findPlace(riverDrop,place);
}


@PutMapping
("/addPlace")
public void addPlace(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "place") Place place){
riverdropdao.addPlace(riverDrop,place);
}


@GetMapping
("/deletePlace")
public boolean deletePlace(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "place") Place place){
  return riverdropdao.deletePlace(riverDrop,place);
}


@GetMapping
("/addComment")
public RiverDropComment addComment(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "account") Account account,@RequestParam(name = "commentText") String commentText){
  return riverdropdao.addComment(riverDrop,account,commentText);
}


@GetMapping
("/deleteComment")
public boolean deleteComment(@RequestParam(name = "commentId") Long commentId){
  return riverdropdao.deleteComment(commentId);
}


@GetMapping
("/findForm")
public RiverDropForm findForm(@RequestParam(name = "dropId") Long dropId,@RequestParam(name = "formId") Long formId){
  return riverdropdao.findForm(dropId,formId);
}


@GetMapping
("/isRead")
public boolean isRead(@RequestParam(name = "riverDrop") RiverDrop riverDrop,@RequestParam(name = "account") Account account){
  return riverdropdao.isRead(riverDrop,account);
}


}