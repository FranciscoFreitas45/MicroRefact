package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GeoLocationDocumentDescriptorController {

 private GeoLocationDocumentDescriptor geolocationdocumentdescriptor;


@GetMapping
("/builder")
public Object builder(@RequestParam(name = "Object") Object Object){
  return geolocationdocumentdescriptor.builder(Object);
}


@GetMapping
("/type")
public Object type(@RequestParam(name = "Object") Object Object){
  return geolocationdocumentdescriptor.type(Object);
}


@GetMapping
("/locationColumnName")
public Object locationColumnName(@RequestParam(name = "Object") Object Object){
  return geolocationdocumentdescriptor.locationColumnName(Object);
}


@GetMapping
("/getLocationColumnName")
public Object getLocationColumnName(@RequestParam(name = "Object") Object Object){
  return geolocationdocumentdescriptor.getLocationColumnName(Object);
}


}