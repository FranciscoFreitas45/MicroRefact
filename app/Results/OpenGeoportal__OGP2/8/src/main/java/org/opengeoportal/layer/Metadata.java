package org.opengeoportal.layer;
 import org.opengeoportal.Interface.BoundingBox;
public class Metadata {

 private BoundingBox bounds;

 private String id;

 private String title;

 private String description;

 private String owsName;

 private String workspaceName;

 private String location;

 private String originator;

 private String[] themeKeywords;

 private String[] placeKeywords;

 private String institution;

 private String fullText;

 private AccessLevel access;

 private GeometryType geometryType;

 private  String publisher;

 private  Boolean georeferenced;

 private  String contentDate;

public Metadata(String layerId) {
    setId(layerId);
}public Metadata() {
}
public String getLocation(){
    return location;
}


public void setContentDate(String contentDate){
    this.contentDate = contentDate;
}


public String getId(){
    return id;
}


public String getOriginator(){
    return originator;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getContentDate(){
    return contentDate;
}


public BoundingBox getBounds(){
    return bounds;
}


public void setAccess(AccessLevel access){
    this.access = access;
}


public String getTitle(){
    return title;
}


public void setInstitution(String institution){
    this.institution = institution;
}


public void setLocation(String location){
    this.location = location;
}


public void setId(String id){
    this.id = id;
}


public void setThemeKeywords(String[] themeKeywords){
    this.themeKeywords = themeKeywords;
}


public void setPlaceKeywords(String[] placeKeywords){
    this.placeKeywords = placeKeywords;
}


public void setAccessLevel(String accessString){
    this.access = AccessLevel.parseString(accessString);
}


public void setGeometryType(String geometryTypeString){
    this.geometryType = GeometryType.parseGeometryType(geometryTypeString);
}


public String[] getPlaceKeywords(){
    return placeKeywords;
}


public void setOriginator(String originator){
    this.originator = originator;
}


public String getFullText(){
    return fullText;
}


public AccessLevel getAccess(){
    return access;
}


public GeometryType getGeometryType(){
    return geometryType;
}


public void setOwsName(String owsName){
    this.owsName = owsName;
}


public void setTitle(String title){
    this.title = title;
}


public String getOwsName(){
    return owsName;
}


public void setBounds(String minX,String minY,String maxX,String maxY){
    this.bounds = new BoundingBox(minX, minY, maxX, maxY);
}


public void setWorkspaceName(String workspaceName){
    this.workspaceName = workspaceName;
}


public String[] getThemeKeywords(){
    return themeKeywords;
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


public void setFullText(String fullText){
    this.fullText = fullText;
}


public Boolean getGeoreferenced(){
    return georeferenced;
}


public void setPublisher(String publisher){
    this.publisher = publisher;
}


public String getInstitution(){
    return institution;
}


}