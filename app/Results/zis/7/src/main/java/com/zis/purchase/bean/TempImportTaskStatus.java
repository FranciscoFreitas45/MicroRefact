package com.zis.purchase.bean;
 import java.util.HashMap;
import java.util.Map;
public class TempImportTaskStatus {

 public  Integer IMPORT_COMPLETE;

 public  Integer FULLY_MATCHED;

 public  Integer SUCCESS;

 public  Integer CANCEL;

 private  Map<Integer,String> displayMap;


public String getDisplay(Integer tempImportTaskStatus){
    return displayMap.get(tempImportTaskStatus);
}


}