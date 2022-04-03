package cn.maxcj.DTO;
 public class ZTreeNode {

 private  Long id;

 private  Long pId;

 private  String name;

 private  Boolean open;

 private  Boolean checked;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getName(){
    return name;
}


public Boolean getOpen(){
    return open;
}


public Long getId(){
    return id;
}


public Boolean getIsOpen(){
    return open;
}


public Long getpId(){
    return pId;
}


public Boolean getChecked(){
    return checked;
}


public ZTreeNode createParent(){
    ZTreeNode zTreeNode = new ZTreeNode();
    zTreeNode.setChecked(true);
    zTreeNode.setId(0L);
    zTreeNode.setName("顶级");
    zTreeNode.setOpen(true);
    zTreeNode.setpId(0L);
    return zTreeNode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createParent"))

;
ZTreeNode aux = restTemplate.getForObject(builder.toUriString(),ZTreeNode.class);
return aux;
}


}