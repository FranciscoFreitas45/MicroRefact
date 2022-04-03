package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorViewRepositoryController {

 private HUEditorViewRepository hueditorviewrepository;


@GetMapping
("/retrieveForHUId")
public HUEditorRow retrieveForHUId(@RequestParam(name = "huId") HuId huId){
  return hueditorviewrepository.retrieveForHUId(huId);
}


}