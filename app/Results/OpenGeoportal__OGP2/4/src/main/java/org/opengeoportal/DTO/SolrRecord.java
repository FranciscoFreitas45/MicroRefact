package org.opengeoportal.DTO;
 import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.beans.Field;
public class SolrRecord {

 private String layerId;

 private String name;

 private String collectionId;

 private String institution;

 private String access;

 private String dataType;

 private String availability;

 private String layerDisplayName;

 private String publisher;

 private String originator;

 private String themeKeywords;

 private String placeKeywords;

 private Boolean georeferenced;

 private String description;

 private String location;

 private Double maxY;

 private Double minY;

 private Double maxX;

 private Double minX;

 private Double centerX;

 private Double centerY;

 private Double halfWidth;

 private Double halfHeight;

 private Double area;

 private String workspaceName;

 private Date contentDate;

 private String fgdcText;


public String getLocation(){
    return location;
}


public String getName(){
    return name;
}


public String getDataType(){
    return dataType;
}


public String getOriginator(){
    return originator;
}


public Date getContentDate(){
    return contentDate;
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


public String getThemeKeywords(){
    return themeKeywords;
}


public String getFgdcText(){
    return fgdcText;
}


public Boolean getGeoreferenced(){
    return georeferenced;
}


public String getCollectionId(){
    return collectionId;
}


public Double getMinX(){
    return minX;
}


public Double getMinY(){
    return minY;
}


public String getDescription(){
    return description;
}


public String getAvailability(){
    return availability;
}


public String getLayerId(){
    return layerId;
}


public String getAccess(){
    return access;
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


public String getPublisher(){
    return publisher;
}


public String getInstitution(){
    return institution;
}


public Double getArea(){
    return area;
}


}