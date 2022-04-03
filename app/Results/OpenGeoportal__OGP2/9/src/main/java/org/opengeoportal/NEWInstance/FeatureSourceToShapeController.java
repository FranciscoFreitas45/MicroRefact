package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FeatureSourceToShapeController {

 private FeatureSourceToShape featuresourcetoshape;


@GetMapping
("/getFeatureSourceRetriever")
public FeatureSourceRetriever getFeatureSourceRetriever(){
  return featuresourcetoshape.getFeatureSourceRetriever();
}


@PutMapping
("/setFeatureCollectionBBox")
public void setFeatureCollectionBBox(@RequestParam(name = "bbox") Envelope bbox){
featuresourcetoshape.setFeatureCollectionBBox(bbox);
}


@GetMapping
("/exportToShapefiles")
public Set<File> exportToShapefiles(){
  return featuresourcetoshape.exportToShapefiles();
}


}