package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentPrintController {

 private DocumentPrint documentprint;


@GetMapping
("/builder")
public Object builder(@RequestParam(name = "Object") Object Object){
  return documentprint.builder(Object);
}


@GetMapping
("/filename")
public Object filename(@RequestParam(name = "Object") Object Object){
  return documentprint.filename(Object);
}


}