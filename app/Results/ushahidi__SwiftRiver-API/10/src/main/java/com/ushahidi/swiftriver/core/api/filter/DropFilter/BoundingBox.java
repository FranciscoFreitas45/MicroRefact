package com.ushahidi.swiftriver.core.api.filter.DropFilter;
 import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ushahidi.swiftriver.core.api.exception.InvalidFilterException;
public class BoundingBox {

 private  Float latFrom;

 private  Float lngFrom;

 private  Float latTo;

 private  Float lngTo;

/**
 * Initializes the bounding box
 *
 * @param latFrom
 * @param lngFrom
 * @param latTo
 * @param lngTo
 */
public BoundingBox(Float latFrom, Float lngFrom, Float latTo, Float lngTo) {
    setLatFrom(latFrom);
    setLngFrom(lngFrom);
    setLatTo(latTo);
    setLngTo(lngTo);
}
public void setLatTo(Float latTo){
    this.latTo = latTo;
}


public Float getLngFrom(){
    return lngFrom;
}


public Float getLatTo(){
    return latTo;
}


public Float getLngTo(){
    return lngTo;
}


public Float getLatFrom(){
    return latFrom;
}


public void setLngFrom(Float lngFrom){
    this.lngFrom = lngFrom;
}


public void setLatFrom(Float latFrom){
    this.latFrom = latFrom;
}


public void setLngTo(Float lngTo){
    this.lngTo = lngTo;
}


}