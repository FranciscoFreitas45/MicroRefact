package cn.offway.athena.dto;
 public class TemplateParam {

 private  String name;

 private  String value;

 private  String color;

public TemplateParam(String name, String value, String color) {
    this.name = name;
    this.value = value;
    this.color = color;
}
public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public void setColor(String color){
    this.color = color;
}


public void setValue(String value){
    this.value = value;
}


public String getColor(){
    return color;
}


}