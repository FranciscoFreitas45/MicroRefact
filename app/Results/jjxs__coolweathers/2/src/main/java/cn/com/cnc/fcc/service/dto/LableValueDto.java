package cn.com.cnc.fcc.service.dto;
 public class LableValueDto {

 private  Long id;

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


public void setId(Long id){
    this.id = id;
}


public void setLabel(String label){
    this.label = label;
}


public Long getId(){
    return id;
}


}