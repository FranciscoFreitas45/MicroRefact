package org.gliderwiki.web.vo;
 import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class TempVo extends BaseObjectBean{

 private  long serialVersionUID;

 private  String id;

 private  String name;

public TempVo(String id, String name) {
    this.id = id;
    this.name = name;
}public TempVo() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}