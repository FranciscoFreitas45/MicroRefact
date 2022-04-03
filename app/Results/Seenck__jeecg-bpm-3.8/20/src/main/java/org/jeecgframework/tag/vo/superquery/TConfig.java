package org.jeecgframework.tag.vo.superquery;
 import java.util.ArrayList;
import java.util.List;
public class TConfig {

 private  String table;

 private  List<Field> fields;


public List<Field> getFields(){
    return fields;
}


public String getTable(){
    return table;
}


public void setTable(String table){
    this.table = table;
}


public void addField(Field f){
    fields.add(f);
}


public void setFields(List<Field> fields){
    this.fields = fields;
}


}