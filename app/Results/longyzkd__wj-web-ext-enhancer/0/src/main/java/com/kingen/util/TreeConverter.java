package com.kingen.util;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingen.bean.RightData;
import com.kingen.util.mapper.JsonMapper;
import com.kingen.vo.TreeNode;
import Interface.JsonMapper;
import DTO.JsonMapper;
public class TreeConverter {

 private  JsonMapper mapper;

 private  List<TreeNode> list;


public List<TreeNode> getChildren(List<TreeNode> all,String id){
    for (TreeNode node : all) {
        // 遍历出父id等于参数的id，add进子节点集合
        if (StringUtils.equals(id, node.getParentId())) {
            // 递归遍历下一级
            getChildren(all, node.getId());
            // 全局变量的使用
            list.add(node);
        }
    }
    return list;
}


public TreeNode toOneComplexTree(List<TreeNode> src){
    Map<String, TreeNode> lookup = Maps.newHashMap();
    for (TreeNode o : src) {
        lookup.put(o.getId(), o);
    }
    Set<String> keySet = lookup.keySet();
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        String parentId = value.getParentId();
        TreeNode parentNode = lookup.get(parentId);
        if (parentNode != null) {
            // 有父节点
            parentNode.addChild(value);
            parentNode.setLeaf(false);
            value.setParent(parentNode);
        }
    }
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        if (value.getParent() == null) {
            return value;
        }
    }
    return null;
}


public List<Map<String,Object>> tree(List<RightData> ts){
    // 一级
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (ts.size() < 1)
        return list;
    for (RightData rightData : ts) {
        if (StringUtils.isEmpty(rightData.getPmenuId())) {
            Map<String, Object> child = new HashMap<String, Object>();
            child.put("menuId", rightData.getMenuId());
            child.put("pmenuId", rightData.getPmenuId());
            child.put("menuName", rightData.getMenuName());
            child.put("checked", rightData.getChecked());
            child.put("expanded", rightData.getExpanded());
            child.put("note", rightData.getNote());
            child.put("children", getChildren(ts, rightData));
            child.put("leaf", CollectionUtils.isEmpty(getChildren(ts, rightData)) ? true : false);
            list.add(child);
        }
    }
    return list;
}


public void main(String[] a){
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    boolean s = CollectionUtils.isEmpty(list) ? true : false;
    // true
    System.out.println(s);
}


public String toComplexJsonString(List<TreeNode> src){
    Map<String, TreeNode> lookup = Maps.newHashMap();
    for (TreeNode o : src) {
        lookup.put(o.getId(), o);
    }
    Set<String> keySet = lookup.keySet();
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        String parentId = value.getParentId();
        TreeNode parentNode = lookup.get(parentId);
        if (parentNode != null) {
            // 有父节点
            parentNode.addChild(value);
            value.setParent(parentNode);
        }
    }
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        if (value.getParent() == null) {
            // ....
            // return  mapper.toJson(Lists.newArrayList(value) );
            return JsonMapper.toJsonString(Lists.newArrayList(value));
        }
    }
    return "";
}


public List<TreeNode> toComplexTree(List<TreeNode> src){
    Map<String, TreeNode> lookup = Maps.newHashMap();
    for (TreeNode o : src) {
        lookup.put(o.getId(), o);
    }
    Set<String> keySet = lookup.keySet();
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        String parentId = value.getParentId();
        TreeNode parentNode = lookup.get(parentId);
        if (parentNode != null) {
            // 有父节点
            parentNode.addChild(value);
            parentNode.setLeaf(false);
            value.setParent(parentNode);
        }
    }
    List<TreeNode> multipleTree = Lists.newArrayList();
    for (String id : keySet) {
        TreeNode value = lookup.get(id);
        if (value.getParent() == null) {
            multipleTree.add(value);
        }
    }
    return multipleTree;
}


}