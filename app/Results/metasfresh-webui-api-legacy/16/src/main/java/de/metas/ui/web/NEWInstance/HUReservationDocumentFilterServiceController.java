package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUReservationDocumentFilterServiceController {

 private HUReservationDocumentFilterService hureservationdocumentfilterservice;


@GetMapping
("/createDocumentFilterIgnoreAttributes")
public DocumentFilter createDocumentFilterIgnoreAttributes(@RequestParam(name = "packageable") Packageable packageable){
  return hureservationdocumentfilterservice.createDocumentFilterIgnoreAttributes(packageable);
}


}