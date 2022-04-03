package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DefaultHUEditorViewFactoryController {

 private DefaultHUEditorViewFactory defaulthueditorviewfactory;


@GetMapping
("/getSqlViewBinding")
public Object getSqlViewBinding(@RequestParam(name = "Object") Object Object){
  return defaulthueditorviewfactory.getSqlViewBinding(Object);
}


}