package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorRowIsProcessedPredicateController {

 private HUEditorRowIsProcessedPredicate hueditorrowisprocessedpredicate;


@GetMapping
("/isProcessed")
public boolean isProcessed(@RequestParam(name = "hu") I_M_HU hu){
  return hueditorrowisprocessedpredicate.isProcessed(hu);
}


}