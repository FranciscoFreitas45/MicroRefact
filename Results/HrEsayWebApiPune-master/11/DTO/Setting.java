import javax.persistence;
public class Setting {

 private  int settingId;

 private  String group;

 private  String key;

 private  String value;

 private  int serialized;

 private  int editable;

 private  String labels;

 private  String defaultValue;

 private  String explaination;

 private  int exInt1;

 private  String exVar1;


public String getKey(){
    return key;
}


public int getExInt1(){
    return exInt1;
}


public int getSerialized(){
    return serialized;
}


public int getEditable(){
    return editable;
}


public String getExVar1(){
    return exVar1;
}


public String getExplaination(){
    return explaination;
}


public String getDefaultValue(){
    return defaultValue;
}


public String getValue(){
    return value;
}


public String getLabels(){
    return labels;
}


public int getSettingId(){
    return settingId;
}


public String getGroup(){
    return group;
}


}