package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileController {

 private File file;

 private File file;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
file.setId(id);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
file.setName(name);
}


@PutMapping
("/setQuestionUid")
public void setQuestionUid(@RequestParam(name = "questionUid") String questionUid){
file.setQuestionUid(questionUid);
}


@GetMapping
("/copy")
public File copy(@RequestParam(name = "fileDir") String fileDir){
  return file.copy(fileDir);
}


@PutMapping
("/setWidth")
public void setWidth(@RequestParam(name = "width") Integer width){
file.setWidth(width);
}


@PutMapping
("/setUid")
public void setUid(@RequestParam(name = "uid") String uid){
file.setUid(uid);
}


@PutMapping
("/setComment")
public void setComment(@RequestParam(name = "comment") String comment){
file.setComment(comment);
}


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
file.setDescription(description);
}


@PutMapping
("/setPosition")
public void setPosition(@RequestParam(name = "position") Integer position){
file.setPosition(position);
}


@PutMapping
("/setLongdesc")
public void setLongdesc(@RequestParam(name = "longdesc") String longdesc){
file.setLongdesc(longdesc);
}


}