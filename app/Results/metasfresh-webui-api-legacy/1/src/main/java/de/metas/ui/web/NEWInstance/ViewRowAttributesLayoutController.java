package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewRowAttributesLayoutController {

 private ViewRowAttributesLayout viewrowattributeslayout;


@GetMapping
("/of")
public ViewRowAttributesLayout of(@RequestParam(name = "elements") List<DocumentLayoutElementDescriptor> elements){
  return viewrowattributeslayout.of(elements);
}


}