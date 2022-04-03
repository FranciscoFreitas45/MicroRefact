package com.gbcom.DTO;
 import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import java.util.ArrayList;
import java.util.List;
public class ZTreeBranch {

 private List<Node> treeNodeList;

 private String[] icons;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String[] getIcons(){
    return icons;
}


public String getIconPath(String[] icons,int no){
    String iconPath = "default.gif";
    if (icons != null) {
        if (icons.length > no) {
            iconPath = icons[no];
        }
    }
    return "../html/img/icon/" + iconPath;
}


public List<Node> getTreeNodeList(){
    if (treeNodeList == null) {
        treeNodeList = new ArrayList<Node>();
    }
    return treeNodeList;
}


public ZTreeNode getRootNode(String name){
    ZTreeNode treeNode = new ZTreeNode();
    treeNode.setId("root");
    treeNode.setIsParent(true);
    treeNode.setIsLeaf(false);
    treeNode.setOpen(true);
    // treeNode.setHasChildren(true);
    treeNode.setName(name);
    treeNode.setText(name);
    treeNode.setUid("root");
    treeNode.setIcon("0");
    treeNode.setType("root");
    return treeNode;
}


public void setIcons(String[] icons){
    this.icons = icons;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIcons"))

.queryParam("icons",icons)
;
restTemplate.put(builder.toUriString(),null);
}


public void addTreeNode(Node treeNode){
    getTreeNodeList().add(treeNode);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addTreeNode"))

.queryParam("treeNode",treeNode)
;
restTemplate.put(builder.toUriString(),null);
}


public String toJsonString(boolean hasCheckBox){
    List<Node> list = getTreeNodeList();
    for (Node node : list) {
        parseNode(node);
    }
    JsonConfig jsonConfig = new JsonConfig();
    if (!hasCheckBox) {
        jsonConfig.setExcludes(new String[] { "checked" });
    }
    JSONArray tranfer = JSONArray.fromObject(list, jsonConfig);
    return tranfer.toString();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toJsonString"))

.queryParam("hasCheckBox",hasCheckBox)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}