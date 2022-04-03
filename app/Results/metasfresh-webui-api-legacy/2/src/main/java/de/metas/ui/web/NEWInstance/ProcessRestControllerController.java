package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessRestControllerController {

 private ProcessRestController processrestcontroller;


@PutMapping
("/cacheReset")
public void cacheReset(){
processrestcontroller.cacheReset();
}


@GetMapping
("/streamDocumentRelatedProcesses")
public Stream<WebuiRelatedProcessDescriptor> streamDocumentRelatedProcesses(@RequestParam(name = "preconditionsContext") WebuiPreconditionsContext preconditionsContext){
  return processrestcontroller.streamDocumentRelatedProcesses(preconditionsContext);
}


@GetMapping
("/filter")
public Object filter(@RequestParam(name = "Object") Object Object){
  return processrestcontroller.filter(Object);
}


}