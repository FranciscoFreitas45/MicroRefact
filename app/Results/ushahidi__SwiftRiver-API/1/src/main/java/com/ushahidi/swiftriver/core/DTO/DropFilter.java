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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


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


public void setDropIds(List<Long> dropIds){
    this.dropIds = dropIds;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDropIds"))

.queryParam("dropIds",dropIds)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaxId(Long maxId){
    this.maxId = maxId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaxId"))

.queryParam("maxId",maxId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSinceId(Long sinceId){
    this.sinceId = sinceId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSinceId"))

.queryParam("sinceId",sinceId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChannels(List<String> channels){
    this.channels = channels;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChannels"))

.queryParam("channels",channels)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChannelIds(List<Long> channelIds){
    this.channelIds = channelIds;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChannelIds"))

.queryParam("channelIds",channelIds)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateFrom(Date dateFrom) throws InvalidFilterException{
    if (dateFrom == null)
        return;
    this.dateFrom = dateFrom;
    if (dateTo != null && dateFrom.after(dateTo)) {
        throw new InvalidFilterException(String.format("dateFrom %s cannot be later than dateTo %s", dateFrom.toString(), dateTo.toString()));
    }
    verifyDates();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDateFrom"))

.queryParam("dateFrom",dateFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateTo(Date dateTo) throws InvalidFilterException{
    if (dateTo == null)
        return;
    this.dateTo = dateTo;
    if (dateFrom != null && dateTo.before(dateFrom)) {
        throw new InvalidFilterException(String.format("dateTo %s cannot be earlier than dateFrom %s", dateTo.toString(), dateFrom.toString()));
    }
    verifyDates();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDateTo"))

.queryParam("dateTo",dateTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRead(Boolean read){
    this.read = read;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRead"))

.queryParam("read",read)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPhotos(Boolean photos){
    this.photos = photos;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPhotos"))

.queryParam("photos",photos)
;
restTemplate.put(builder.toUriString(),null);
}


public void setKeywords(String keywords){
    this.keywords = keywords;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setKeywords"))

.queryParam("keywords",keywords)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBoundingBox(String boundingBoxStr) throws InvalidFilterException{
    if (boundingBoxStr == null)
        return;
    // Validate the location bounds
    String[] bounds = boundingBoxStr.split(",");
    if (bounds.length != 4) {
        throw new InvalidFilterException(String.format("Invalid bounding box '[%s]'", boundingBoxStr));
    }
    // Get the bounding box values
    float latFrom = Float.parseFloat(bounds[0]);
    float lngFrom = Float.parseFloat(bounds[1]);
    float latTo = Float.parseFloat(bounds[2]);
    float lngTo = Float.parseFloat(bounds[3]);
    // Validate each value
    if (!isValidLatitude(latFrom)) {
        throw new InvalidFilterException(String.format("Invalid latitude in bounding box: %f", latFrom));
    }
    if (!isValidLongitude(lngFrom)) {
        throw new InvalidFilterException(String.format("Invalid longitude in bounding box %f", lngFrom));
    }
    if (!isValidLatitude(latTo)) {
        throw new InvalidFilterException(String.format("Invalid latitude in bounding box: %f", latTo));
    }
    if (!isValidLongitude(lngTo)) {
        throw new InvalidFilterException(String.format("Invalid longitude in bounding box %f", lngTo));
    }
    // Verify that the SouthWest corner is the first pair
    // in the bounding box
    if ((latFrom > latTo) || (lngFrom > lngTo)) {
        throw new InvalidFilterException(String.format("Invalid bounding box: '%s'. The SouthWest corner should be specified first", boundingBoxStr));
    }
    // Create and set the bounding box
    this.boundingBox = new BoundingBox(latFrom, lngFrom, latTo, lngTo);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBoundingBox"))

.queryParam("boundingBoxStr",boundingBoxStr)
;
restTemplate.put(builder.toUriString(),null);
}


}