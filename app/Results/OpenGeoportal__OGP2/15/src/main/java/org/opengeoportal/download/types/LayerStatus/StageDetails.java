package org.opengeoportal.download.types.LayerStatus;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StageDetails {

 private List<Map<String,String>> successMessage;

 private List<Map<String,String>> errorMessage;

 private List<Map<String,String>> warningMessage;


public void addError(String name,String message){
    errorMessage.add(statusMessage(name, message));
}


public void addSuccess(String name,String message){
    successMessage.add(statusMessage(name, message));
}


public List<Map<String,String>> getWarnings(){
    return warningMessage;
}


public void addWarning(String name,String message){
    warningMessage.add(statusMessage(name, message));
}


public List<Map<String,String>> getSuccesses(){
    return successMessage;
}


public List<Map<String,String>> getErrors(){
    return errorMessage;
}


public Map<String,String> statusMessage(String layerName,String status){
    Map<String, String> statusMap = new HashMap<String, String>();
    statusMap.put("layer", layerName);
    statusMap.put("status", status);
    return statusMap;
}


}