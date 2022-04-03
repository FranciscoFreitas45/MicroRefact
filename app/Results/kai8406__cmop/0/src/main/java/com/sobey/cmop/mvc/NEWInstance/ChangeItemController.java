package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChangeItemController {

 private ChangeItem changeitem;

 private ChangeItem changeitem;


@PutMapping
("/setNewString")
public void setNewString(@RequestParam(name = "newString") String newString){
changeitem.setNewString(newString);
}


@PutMapping
("/setChange")
public void setChange(@RequestParam(name = "change") Change change){
changeitem.setChange(change);
}


@PutMapping
("/setFieldName")
public void setFieldName(@RequestParam(name = "fieldName") String fieldName){
changeitem.setFieldName(fieldName);
}


@PutMapping
("/setOldValue")
public void setOldValue(@RequestParam(name = "oldValue") String oldValue){
changeitem.setOldValue(oldValue);
}


@PutMapping
("/setOldString")
public void setOldString(@RequestParam(name = "oldString") String oldString){
changeitem.setOldString(oldString);
}


}