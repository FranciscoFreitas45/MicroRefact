package cn.maxcj.core.common.node;
 public class ZTreeNode {

 private  Long id;

 private  Long pId;

 private  String name;

 private  Boolean open;

 private  Boolean checked;


public void setName(String name){
    this.name = name;
}


public void setOpen(Boolean open){
    this.open = open;
}


public String getName(){
    return name;
}


public Boolean getOpen(){
    return open;
}


public void setIsOpen(Boolean open){
    this.open = open;
}


public Long getId(){
    return id;
}


public void setpId(Long pId){
    this.pId = pId;
}


public Boolean getIsOpen(){
    return open;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public void setId(Long id){
    this.id = id;
}


public ZTreeNode createParent(){
    ZTreeNode zTreeNode = new ZTreeNode();
    zTreeNode.setChecked(true);
    zTreeNode.setId(0L);
    zTreeNode.setName("顶级");
    zTreeNode.setOpen(true);
    zTreeNode.setpId(0L);
    return zTreeNode;
}


public Long getpId(){
    return pId;
}


public Boolean getChecked(){
    return checked;
}


}