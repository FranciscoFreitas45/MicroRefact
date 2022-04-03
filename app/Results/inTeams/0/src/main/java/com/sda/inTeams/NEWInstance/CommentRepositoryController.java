package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import java.util.*;
 import com.sda.inTeams.DTO.*;
import com.sda.inTeams.repository.*;
 import com.sda.inTeams.model.Comment.*;
  import com.sda.inTeams.model.Task.*;
@RestController
@CrossOrigin
public class CommentRepositoryController {

 private CommentRepository commentrepository;


@GetMapping
("/findAllByCreator")
public List<Comment> findAllByCreator(@RequestParam(name = "user") User user){
  return commentrepository.findAllByCreator(user);
}

/*
@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return commentrepository.count(Object);
}
*/

@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return commentrepository.saveAll((List<Comment>)Object);
}


@GetMapping
("/findAllByTask")
public List<Comment> findAllByTask(@RequestParam(name = "task") Task task){
  return commentrepository.findAllByTask(task);
}


}