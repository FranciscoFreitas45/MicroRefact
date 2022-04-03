package com.gbcom.system.tree;
 import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import java.util.ArrayList;
import java.util.List;
public class ZTreeBranch {

 private List<Node> treeNodeList;

 private String[] icons;


public void setTreeNodeList(List<Node> treeNodeList){
    this.treeNodeList = treeNodeList;
}


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


public void setIcons(String[] icons){
    this.icons = icons;
}


public void addTreeNode(Node treeNode){
    getTreeNodeList().add(treeNode);
}


public List<Node> getTreeNodeList(){
    if (treeNodeList == null) {
        treeNodeList = new ArrayList<Node>();
    }
    return treeNodeList;
}


public Node processNode(Node node){
    ZTreeNode treeNode = (ZTreeNode) node;
    if (treeNode.getIcon() != null) {
        treeNode.setIcon(getIconPath(getIcons(), Integer.parseInt(treeNode.getIcon())));
    } else {
        treeNode.setIcon(getIconPath(getIcons(), 0));
    }
    // 建设controller设置代码，如业务复杂可以开放uid应用
    String uid = treeNode.getType() + "|<id>" + treeNode.getId() + "</id>";
    if (treeNode.getUid() == null) {
        treeNode.setUid(uid);
    }
    if (treeNode.getText() != null && !"".equals(treeNode.getText())) {
        treeNode.setText(treeNode.getText());
    } else {
        treeNode.setText("");
    }
    treeNode.setOpen(false);
    treeNode.setIsParent(!treeNode.getIsLeaf());
    return treeNode;
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


public void parseNode(Node node){
    processNode(node);
    if (((ZTreeNode) node).getChildren().size() > 0) {
        for (Node node1 : ((ZTreeNode) node).getChildren()) {
            parseNode(node1);
        }
    }
}


}