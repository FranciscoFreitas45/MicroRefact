package DTO;
 import java.util.HashMap;
import java.util.Map;
public class DSDataEvent {

 public  DSData dsData;

 private  String orgi;

 private  String tablename;

 private  String batid;

 private  Map<String,Object> values;

 private  boolean failures;

 private  long times;

 private  String message;


public long getTimes(){
    return times;
}


public DSData getDSData(){
    return dsData;
}


public Map<String,Object> getValues(){
    return values;
}


public String getMessage(){
    return message;
}


public String getBatid(){
    return batid;
}


public String getTablename(){
    return tablename;
}


public String getOrgi(){
    return orgi;
}


}