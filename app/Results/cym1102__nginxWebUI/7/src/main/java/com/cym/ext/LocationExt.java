package com.cym.ext;
 import com.cym.model.Location;
import com.cym.model.Upstream;
import com.cym.Interface.Upstream;
public class LocationExt {

 private Location location;

 private Upstream upstream;


public Location getLocation(){
    return location;
}


public Upstream getUpstream(){
    return upstream;
}


public void setLocation(Location location){
    this.location = location;
}


public void setUpstream(Upstream upstream){
    this.upstream = upstream;
}


}