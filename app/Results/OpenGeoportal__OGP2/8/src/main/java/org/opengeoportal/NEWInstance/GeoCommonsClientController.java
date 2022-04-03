package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GeoCommonsClientController {

 private GeoCommonsClient geocommonsclient;


@PutMapping
("/initializeClient")
public void initializeClient(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password){
geocommonsclient.initializeClient(username,password);
}


@PutMapping
("/checkUser")
public void checkUser(@RequestParam(name = "username") String username){
geocommonsclient.checkUser(username);
}


@GetMapping
("/uploadWmsDataSet")
public String uploadWmsDataSet(@RequestParam(name = "layerId") String layerId){
  return geocommonsclient.uploadWmsDataSet(layerId);
}


@GetMapping
("/checkDataSetStatus")
public DataSetStatus checkDataSetStatus(@RequestParam(name = "location") String location){
  return geocommonsclient.checkDataSetStatus(location);
}


@GetMapping
("/createMap")
public String createMap(@RequestParam(name = "basemap") String basemap,@RequestParam(name = "extent") String extent,@RequestParam(name = "title") String title,@RequestParam(name = "description") String description){
  return geocommonsclient.createMap(basemap,extent,title,description);
}


@PutMapping
("/addLayerToMap")
public void addLayerToMap(@RequestParam(name = "mapId") String mapId,@RequestParam(name = "dataSetStatus") DataSetStatus dataSetStatus){
geocommonsclient.addLayerToMap(mapId,dataSetStatus);
}


}