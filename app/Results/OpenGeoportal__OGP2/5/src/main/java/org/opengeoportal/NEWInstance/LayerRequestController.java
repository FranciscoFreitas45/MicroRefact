package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LayerRequestController {

 private LayerRequest layerrequest;


@PutMapping
("/setMetadata")
public void setMetadata(@RequestParam(name = "metadata") Boolean metadata){
layerrequest.setMetadata(metadata);
}


@GetMapping
("/getLayerInfo")
public SolrRecord getLayerInfo(){
  return layerrequest.getLayerInfo();
}


@GetMapping
("/getRequestedBounds")
public BoundingBox getRequestedBounds(){
  return layerrequest.getRequestedBounds();
}


}