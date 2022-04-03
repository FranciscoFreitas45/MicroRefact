package com.ushahidi.swiftriver.core.api.filter;
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


public boolean isValidLongitude(float longitude){
    return longitude <= 180 && longitude >= -180;
}


public Date getDateFrom(){
    return dateFrom;
}


public List<Long> getChannelIds(){
    return channelIds;
}


public void setDropIds(List<Long> dropIds){
    this.dropIds = dropIds;
}


public void setMaxId(Long maxId){
    this.maxId = maxId;
}


public List<Long> getDropIds(){
    return dropIds;
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


public void setLngFrom(Float lngFrom){
    this.lngFrom = lngFrom;
}


public BoundingBox getBoundingBox(){
    return boundingBox;
}


public void setLatFrom(Float latFrom){
    this.latFrom = latFrom;
}


public Boolean getPhotos(){
    return photos;
}


public void verifyDates(){
    if (dateFrom != null && dateTo != null && dateFrom.equals(dateTo)) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTo);
        calendar.add(Calendar.HOUR, 24);
        this.dateTo = calendar.getTime();
    }
}


public void setRead(Boolean read){
    this.read = read;
}


public void setPhotos(Boolean photos){
    this.photos = photos;
}


public Long getSinceId(){
    return sinceId;
}


public void setDateFrom(Date dateFrom) throws InvalidFilterException{
    if (dateFrom == null)
        return;
    this.dateFrom = dateFrom;
    if (dateTo != null && dateFrom.after(dateTo)) {
        throw new InvalidFilterException(String.format("dateFrom %s cannot be later than dateTo %s", dateFrom.toString(), dateTo.toString()));
    }
    verifyDates();
}


public Long getMaxId(){
    return maxId;
}


public void setChannels(List<String> channels){
    this.channels = channels;
}


public void setSinceId(Long sinceId){
    this.sinceId = sinceId;
}


public String getKeywords(){
    return keywords;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public boolean isValidLatitude(float latitude){
    return latitude <= 90 && latitude >= -90;
}


public void setLngTo(Float lngTo){
    this.lngTo = lngTo;
}


public void setLatTo(Float latTo){
    this.latTo = latTo;
}


public void setDateTo(Date dateTo) throws InvalidFilterException{
    if (dateTo == null)
        return;
    this.dateTo = dateTo;
    if (dateFrom != null && dateTo.before(dateFrom)) {
        throw new InvalidFilterException(String.format("dateTo %s cannot be earlier than dateFrom %s", dateTo.toString(), dateFrom.toString()));
    }
    verifyDates();
}


public Date getDateTo(){
    return dateTo;
}


public List<String> getChannels(){
    return channels;
}


public void setChannelIds(List<Long> channelIds){
    this.channelIds = channelIds;
}


public Float getLatTo(){
    return latTo;
}


}