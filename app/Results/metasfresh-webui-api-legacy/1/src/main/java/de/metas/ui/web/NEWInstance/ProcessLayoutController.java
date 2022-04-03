package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessLayoutController {

 private ProcessLayout processlayout;


@GetMapping
("/getDescription")
public ITranslatableString getDescription(){
  return processlayout.getDescription();
}


@GetMapping
("/builder")
public Builder builder(){
  return processlayout.builder();
}


}