package DTO;
 public class ComboTreeModel {

 private  String idField;

 private  String textField;

 private  String iconCls;

 private  String childField;

 private  String srcField;

public ComboTreeModel(String idField, String textField, String childField) {
    this.idField = idField;
    this.textField = textField;
    this.childField = childField;
}public ComboTreeModel(String idField, String textField, String childField, String srcField) {
    this.idField = idField;
    this.textField = textField;
    this.childField = childField;
    this.srcField = srcField;
}
public String getIconCls(){
    return iconCls;
}


public String getIdField(){
    return idField;
}


public String getChildField(){
    return childField;
}


public String getTextField(){
    return textField;
}


public String getSrcField(){
    return srcField;
}


}