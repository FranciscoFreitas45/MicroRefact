package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParamController {

 private Param param;

 private Param param;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
param.setName(name);
}


@PutMapping
("/setLocationId")
public void setLocationId(@RequestParam(name = "locationId") String locationId){
param.setLocationId(locationId);
}


@PutMapping
("/setUpstreamId")
public void setUpstreamId(@RequestParam(name = "upstreamId") String upstreamId){
param.setUpstreamId(upstreamId);
}


}