package org.opengeoportal.download.types;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LayerStatus {

 private  Map<LayerStage,StageDetails> status;

 private List<Map<String,String>> successMessage;

 private List<Map<String,String>> errorMessage;

 private List<Map<String,String>> warningMessage;


public void addError(String name,String message){
    errorMessage.add(statusMessage(name, message));
}


public void addSuccess(String name,String message){
    successMessage.add(statusMessage(name, message));
}


public StageDetails getCurrentDetails(){
    return status.get(getCurrentStage());
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


public Map<LayerStage,StageDetails> getStatus(){
    return status;
}


public LayerStage getCurrentStage(){
    LayerStage[] arrLayerStage = (LayerStage[]) status.keySet().toArray();
    return arrLayerStage[arrLayerStage.length - 1];
}


public List<Map<String,String>> getErrors(){
    return errorMessage;
}


public void setStatus(LayerStage layerStage,StageProgress stageProgress){
}


public Map<String,String> statusMessage(String layerName,String status){
    Map<String, String> statusMap = new HashMap<String, String>();
    statusMap.put("layer", layerName);
    statusMap.put("status", status);
    return statusMap;
}


}