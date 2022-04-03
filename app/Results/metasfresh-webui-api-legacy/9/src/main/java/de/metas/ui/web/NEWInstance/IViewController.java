package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IViewController {

 private IView iview;


@GetMapping
("/getViewId")
public ViewId getViewId(){
  return iview.getViewId();
}


@GetMapping
("/getTableNameOrNull")
public String getTableNameOrNull(){
  return iview.getTableNameOrNull();
}


@GetMapping
("/getClass")
public Object getClass(@RequestParam(name = "Object") Object Object){
  return iview.getClass(Object);
}


@GetMapping
("/getParentViewId")
public ViewId getParentViewId(){
  return iview.getParentViewId();
}


}