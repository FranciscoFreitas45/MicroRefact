package com.cym.ext;
 import java.util.List;
import com.cym.model.Location;
import com.cym.model.Server;
import com.cym.model.Upstream;
public class ServerExt {

 private Server server;

 private List<Location> locationList;

 private String locationStr;

 private String paramJson;


public String getParamJson(){
    return paramJson;
}


public void setParamJson(String paramJson){
    this.paramJson = paramJson;
}


public Server getServer(){
    return server;
}


public String getLocationStr(){
    return locationStr;
}


public void setServer(Server server){
    this.server = server;
}


public void setLocationList(List<Location> locationList){
    this.locationList = locationList;
}


public List<Location> getLocationList(){
    return locationList;
}


public void setLocationStr(String locationStr){
    this.locationStr = locationStr;
}


}