package org.opengeoportal.solr;
 import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.beans.Field;
public class SolrRecord {

@Field("LayerId")
 private String layerId;

@Field("Name")
 private String name;

@Field("CollectionId")
 private String collectionId;

@Field("Institution")
 private String institution;

@Field("Access")
 private String access;

@Field("DataType")
 private String dataType;

@Field("Availability")
 private String availability;

@Field("LayerDisplayName")
 private String layerDisplayName;

@Field("Publisher")
 private String publisher;

@Field("Originator")
 private String originator;

@Field("ThemeKeywords")
 private String themeKeywords;

@Field("PlaceKeywords")
 private String placeKeywords;

@Field("GeoReferenced")
 private Boolean georeferenced;

@Field("Abstract")
 private String description;

@Field("Location")
 private String location;

@Field("MaxY")
 private Double maxY;

@Field("MinY")
 private Double minY;

@Field("MaxX")
 private Double maxX;

@Field("MinX")
 private Double minX;

@Field("CenterX")
 private Double centerX;

@Field("CenterY")
 private Double centerY;

@Field("HalfWidth")
 private Double halfWidth;

@Field("HalfHeight")
 private Double halfHeight;

@Field("Area")
 private Double area;

@Field("WorkspaceName")
 private String workspaceName;

@Field("ContentDate")
 private Date contentDate;

@Field("FgdcText")
 private String fgdcText;


public String getLocation(){
    return location;
}


public String getName(){
    return name;
}


public void setMinY(Double minY){
    this.minY = minY;
}


public void setCenterX(Double centerX){
    this.centerX = centerX;
}


public void setMinX(Double minX){
    this.minX = minX;
}


public String getDataType(){
    return dataType;
}


public void setCenterY(Double centerY){
    this.centerY = centerY;
}


public void setContentDate(Date contentDate){
    this.contentDate = contentDate;
}


public String getOriginator(){
    return originator;
}


public Date getContentDate(){
    return contentDate;
}


public void setAccess(String access){
    this.access = access;
}


public void setInstitution(String institution){
    this.institution = institution;
}


public void setAvailability(String availability){
    this.availability = availability;
}


public void setThemeKeywords(String themeKeywords){
    this.themeKeywords = themeKeywords;
}


public void setCollectionId(String collectionId){
    this.collectionId = collectionId;
}


public void setHalfWidth(Double halfWidth){
    this.halfWidth = halfWidth;
}


public void setPlaceKeywords(String placeKeywords){
    this.placeKeywords = placeKeywords;
}


public Double getCenterY(){
    return centerY;
}


public Double getCenterX(){
    return centerX;
}


public String getPlaceKeywords(){
    return placeKeywords;
}


public Double getHalfWidth(){
    return halfWidth;
}


public void setWorkspaceName(String workspaceName){
    this.workspaceName = workspaceName;
}


public String getThemeKeywords(){
    return themeKeywords;
}


public String getFgdcText(){
    return fgdcText;
}


public void setDataType(String dataType){
    this.dataType = dataType;
}


public void setLayerId(String layerId){
    this.layerId = layerId;
}


public Boolean getGeoreferenced(){
    return georeferenced;
}


public String getCollectionId(){
    return collectionId;
}


public void setMaxY(Double maxY){
    this.maxY = maxY;
}


public void setMaxX(Double maxX){
    this.maxX = maxX;
}


public void setPublisher(String publisher){
    this.publisher = publisher;
}


public void setHalfHeight(Double halfHeight){
    this.halfHeight = halfHeight;
}


public Double getMinX(){
    return minX;
}


public Double getMinY(){
    return minY;
}


public void setName(String name){
    this.name = name;
}


public void setArea(Double area){
    this.area = area;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getAvailability(){
    return availability;
}


public void setLocation(String location){
    this.location = location;
}


public String getLayerId(){
    return layerId;
}


public void setOriginator(String originator){
    this.originator = originator;
}


public String getAccess(){
    return access;
}


public void setFgdcText(String fgdcText){
    this.fgdcText = fgdcText;
}


public void setLayerDisplayName(String layerDisplayName){
    this.layerDisplayName = layerDisplayName;
}


public Double getMaxX(){
    return maxX;
}


public Double getMaxY(){
    return maxY;
}


public Double getHalfHeight(){
    return halfHeight;
}


public String getLayerDisplayName(){
    return layerDisplayName;
}


public String getWorkspaceName(){
    return workspaceName;
}


public void setGeoreferenced(Boolean georeferenced){
    this.georeferenced = georeferenced;
}


public String getPublisher(){
    return publisher;
}


public Map<String,String> toMap(){
    Map<String, String> map = new HashMap<String, String>();
    map.put("LayerId", this.layerId);
    map.put("LayerName", this.name);
    map.put("Title", this.layerDisplayName);
    map.put("DataType", this.dataType);
    map.put("Access", this.access);
    // map.put("ContentDate", this.contentDate);
    map.put("Bounds", this.minX + "," + this.minY + "," + this.maxX + "," + this.maxY);
    map.put("Originator", this.originator);
    map.put("Publisher", this.publisher);
    return map;
}


public String toString(){
    Map<String, String> map = this.toMap();
    String s = "";
    for (String key : map.keySet()) {
        s += key;
        s += ": ";
        s += map.get(key);
        s += ",";
    }
    return s;
}


public String getInstitution(){
    return institution;
}


public Double getArea(){
    return area;
}


}