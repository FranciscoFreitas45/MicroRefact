package com.ushahidi.swiftriver.core.DTO;
 import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ushahidi.swiftriver.core.api.exception.InvalidFilterException;
public class DropFilter {

 private  List<String> channels;

 private  Boolean read;

 private  Boolean photos;

 private  Date dateFrom;

 private  Date dateTo;

 private  Long sinceId;

 private  Long maxId;

 private  List<Long> channelIds;

 private  List<Long> dropIds;

 private  String keywords;

 private  BoundingBox boundingBox;

 private  Float latFrom;

 private  Float lngFrom;

 private  Float latTo;

 private  Float lngTo;


public Date getDateFrom(){
    return dateFrom;
}


public List<Long> getChannelIds(){
    return channelIds;
}


public List<Long> getDropIds(){
    return dropIds;
}


public Boolean getRead(){
    return read;
}


public Float getLngFrom(){
    return lngFrom;
}


public Float getLngTo(){
    return lngTo;
}


public Float getLatFrom(){
    return latFrom;
}


public BoundingBox getBoundingBox(){
    return boundingBox;
}


public Boolean getPhotos(){
    return photos;
}


public Long getSinceId(){
    return sinceId;
}


public Long getMaxId(){
    return maxId;
}


public String getKeywords(){
    return keywords;
}


public Date getDateTo(){
    return dateTo;
}


public List<String> getChannels(){
    return channels;
}


public Float getLatTo(){
    return latTo;
}


}