package com.gbcom.common.template.res;
 import java.util.ResourceBundle;
public class BasicResManager {

 private  String PROPERTIES_FILE_PATH;

 private  ResourceBundle bundle;


public String getString(String key){
    if (bundle == null) {
        bundle = ResourceBundle.getBundle(PROPERTIES_FILE_PATH);
    }
    String value;
    try {
        value = bundle.getString(key);
    } catch (Exception e) {
        e.printStackTrace();
        return key;
    }
    return (value == null || value.equals("")) ? key : value;
}


}