package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MetadataRetrieverController {

 private MetadataRetriever metadataretriever;


@GetMapping
("/getXMLFile")
public File getXMLFile(@RequestParam(name = "metadataFileName") String metadataFileName,@RequestParam(name = "xmlFile") File xmlFile){
  return metadataretriever.getXMLFile(metadataFileName,xmlFile);
}


@GetMapping
("/getContactName")
public String getContactName(@RequestParam(name = "layerID") String layerID){
  return metadataretriever.getContactName(layerID);
}


@GetMapping
("/getContactAddress")
public String getContactAddress(@RequestParam(name = "layerID") String layerID){
  return metadataretriever.getContactAddress(layerID);
}


@GetMapping
("/getContactPhoneNumber")
public String getContactPhoneNumber(@RequestParam(name = "layerId") String layerId){
  return metadataretriever.getContactPhoneNumber(layerId);
}


}