import javax.persistence;
@Entity
@Table(name = "m_setting")
public class Setting {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "setting_id")
 private  int settingId;

@Column(name = "group")
 private  String group;

@Column(name = "key")
 private  String key;

@Column(name = "value")
 private  String value;

@Column(name = "serialized ")
 private  int serialized;

@Column(name = "editable")
 private  int editable;

@Column(name = "labels")
 private  String labels;

@Column(name = "default_value")
 private  String defaultValue;

@Column(name = "explaination")
 private  String explaination;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_var1")
 private  String exVar1;


public void setDefaultValue(String defaultValue){
    this.defaultValue = defaultValue;
}


public String getKey(){
    return key;
}


public int getExInt1(){
    return exInt1;
}


public int getSerialized(){
    return serialized;
}


public void setSerialized(int serialized){
    this.serialized = serialized;
}


public int getEditable(){
    return editable;
}


public String getExVar1(){
    return exVar1;
}


public void setExplaination(String explaination){
    this.explaination = explaination;
}


public String getExplaination(){
    return explaination;
}


public String getDefaultValue(){
    return defaultValue;
}


public void setGroup(String group){
    this.group = group;
}


public String getValue(){
    return value;
}


public String getLabels(){
    return labels;
}


public void setEditable(int editable){
    this.editable = editable;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getSettingId(){
    return settingId;
}


public String getGroup(){
    return group;
}


public void setValue(String value){
    this.value = value;
}


@Override
public String toString(){
    return "Setting [settingId=" + settingId + ", group=" + group + ", key=" + key + ", value=" + value + ", serialized=" + serialized + ", editable=" + editable + ", labels=" + labels + ", defaultValue=" + defaultValue + ", explaination=" + explaination + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
}


public void setSettingId(int settingId){
    this.settingId = settingId;
}


public void setLabels(String labels){
    this.labels = labels;
}


public void setKey(String key){
    this.key = key;
}


}