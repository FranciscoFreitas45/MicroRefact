package org.opengeoportal.download.controllers;
 import org.opengeoportal.download.types.LayerRequest.Status;
import org.opengeoportal.layer.BoundingBox;
public class RequestedLayerStatus {

 private  Status status;

 private  String id;

 private  String bounds;

 private  String name;

 private  String responseType;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(String id){
    this.id = id;
}


public void setResponseType(String responseType){
    this.responseType = responseType;
}


public String getId(){
    return id;
}


public String getResponseType(){
    return responseType;
}


public Status getStatus(){
    return status;
}


public void setBounds(String bounds){
    this.bounds = bounds;
}


public void setStatus(Status status){
    this.status = status;
}


public String getBounds(){
    return bounds;
}


}