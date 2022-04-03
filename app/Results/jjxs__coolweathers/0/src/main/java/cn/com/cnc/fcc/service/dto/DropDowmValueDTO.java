package cn.com.cnc.fcc.service.dto;
 public class DropDowmValueDTO {

 private  String label;

 private  String value;


public String getValue(){
    return value;
}


public String getLabel(){
    return label;
}


public void setValue(String value){
    this.value = value;
}


public void setLabel(String label){
    this.label = label;
}


}