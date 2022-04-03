package org.opengeoportal.utilities;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
public class LocationFieldUtils {

 final  Logger logger;


public Boolean hasKey(String locationField,String key){
    JsonNode rootNode = parseLocationField(locationField);
    JsonNode pathNode = rootNode.path(key);
    if (pathNode.isMissingNode()) {
        return false;
    } else {
        return true;
    }
}


public Boolean hasServiceStart(String locationField){
    try {
        return hasKey(locationField, "serviceStart");
    } catch (JsonParseException e) {
    }
    return false;
}


public JsonNode parseLocationField(String locationField){
    // normalize key names
    locationField = locationField.replaceAll("(?i)\"wms\"", "\"wms\"");
    locationField = locationField.replaceAll("(?i)\"wcs\"", "\"wcs\"");
    locationField = locationField.replaceAll("(?i)\"wfs\"", "\"wfs\"");
    locationField = locationField.replaceAll("(?i)\"serviceStart\"", "\"serviceStart\"");
    locationField = locationField.replaceAll("(?i)\"download\"", "\"fileDownload\"");
    locationField = locationField.replaceAll("(?i)\"fileDownload\"", "\"fileDownload\"");
    locationField = locationField.replaceAll("(?i)\"tilecache\"", "\"tilecache\"");
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = null;
    try {
        rootNode = mapper.readTree(locationField);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return rootNode;
}


public String getWcsUrl(String locationField){
    return parseLocationFromKey(locationField, "wcs").get(0);
}


public String getUrl(String type,String locationField){
    return parseLocationFromKey(locationField, type).get(0);
}


public Boolean hasWfsUrl(String locationField){
    try {
        return hasKey(locationField, "wfs");
    } catch (JsonParseException e) {
    }
    return false;
}


public String getTilecacheUrl(String locationField){
    return parseLocationFromKey(locationField, "tilecache").get(0);
}


public List<String> getDownloadUrl(String locationField){
    return parseLocationFromKey(locationField, "fileDownload");
}


public String getWmsUrl(String locationField){
    return parseLocationFromKey(locationField, "wms").get(0);
}


public String getServiceStartUrl(String locationField){
    return parseLocationFromKey(locationField, "serviceStart").get(0);
}


public Boolean hasWmsUrl(String locationField){
    try {
        return hasKey(locationField, "wms");
    } catch (JsonParseException e) {
    }
    return false;
}


public String getWfsUrl(String locationField){
    return parseLocationFromKey(locationField, "wfs").get(0);
}


public List<String> parseLocationFromKey(String locationField,String key){
    JsonNode rootNode = parseLocationField(locationField);
    JsonNode pathNode = rootNode.path(key);
    Set<String> url = new HashSet<String>();
    if (pathNode.isMissingNode()) {
        throw new JsonParseException("The Object '" + key + "' could not be found.", null);
    } else if (pathNode.isArray()) {
        ArrayNode urls = (ArrayNode) rootNode.path(key);
        for (JsonNode currentUrl : urls) {
            if (currentUrl.isTextual()) {
                url.add(currentUrl.asText());
            } else {
                throw new JsonParseException("Invalid url value in Location field", null);
            }
        }
    } else if (pathNode.isTextual()) {
        url.add(pathNode.asText());
    }
    if (url == null || url.isEmpty()) {
        throw new JsonParseException("The Object '" + key + "' is empty.", null);
    }
    List<String> urlList = new ArrayList<String>();
    urlList.addAll(url);
    return urlList;
}


public Boolean hasArcGISRestUrl(String locationField){
    try {
        return hasKey(locationField, "ArcGISRest");
    } catch (JsonParseException e) {
    }
    return false;
}


public String getArcGISRestUrl(String locationField){
    return parseLocationFromKey(locationField, "ArcGISRest").get(0);
}


}