package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SheetCommentServiceController {

 private SheetCommentService sheetcommentservice;


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.createInBatch(Object);
}


@GetMapping
("/countByStatus")
public Object countByStatus(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.countByStatus(Object);
}


@GetMapping
("/removeByPostId")
public Object removeByPostId(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.removeByPostId(Object);
}


@GetMapping
("/countByStatusAndPostIds")
public Object countByStatusAndPostIds(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.countByStatusAndPostIds(Object);
}


@GetMapping
("/countByPostId")
public Object countByPostId(@RequestParam(name = "Object") Object Object){
  return sheetcommentservice.countByPostId(Object);
}


}