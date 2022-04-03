package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GeoLocationDocumentServiceController {

 private GeoLocationDocumentService geolocationdocumentservice;


@GetMapping
("/hasGeoLocationSupport")
public boolean hasGeoLocationSupport(@RequestParam(name = "fieldNames") Set<String> fieldNames){
  return geolocationdocumentservice.hasGeoLocationSupport(fieldNames);
}


}