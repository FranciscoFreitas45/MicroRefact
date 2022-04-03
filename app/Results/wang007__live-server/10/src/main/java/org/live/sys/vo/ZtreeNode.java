package org.live.sys.vo;
 public class ZtreeNode {

 private  String id;

 private  String pId;

 private  String name;

 private  boolean open;

 private  boolean checked;

public ZtreeNode() {
    open = true;
}public ZtreeNode(String id, String pId, String name) {
    this.id = id;
    this.pId = pId;
    this.name = name;
    open = true;
}
public void setName(String name){
    this.name = name;
}


public void setOpen(boolean open){
    this.open = open;
}


public String getName(){
    return name;
}


public boolean isOpen(){
    return open;
}


public void setChecked(boolean checked){
    this.checked = checked;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setpId(String pId){
    this.pId = pId;
}


public String getpId(){
    return pId;
}


public boolean isChecked(){
    return checked;
}


}