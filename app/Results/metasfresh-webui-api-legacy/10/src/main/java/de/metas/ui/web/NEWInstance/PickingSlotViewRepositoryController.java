package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PickingSlotViewRepositoryController {

 private PickingSlotViewRepository pickingslotviewrepository;


@GetMapping
("/retrievePickingSlotsRows")
public List<PickingSlotRow> retrievePickingSlotsRows(@RequestParam(name = "query") PickingSlotQuery query){
  return pickingslotviewrepository.retrievePickingSlotsRows(query);
}


}