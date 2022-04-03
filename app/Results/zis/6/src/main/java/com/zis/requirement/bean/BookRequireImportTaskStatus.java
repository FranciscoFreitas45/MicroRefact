package com.zis.requirement.bean;
 import java.util.HashMap;
import java.util.Map;
public class BookRequireImportTaskStatus {

 public  String NOT_MATCHED;

 public  String MATCHED;

 public  String IMPORT_SUCCESS;

 private  Map<String,String> map;


public String getDisplay(String status){
    return map.get(status);
}


}