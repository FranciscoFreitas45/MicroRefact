package com.gbcom.common.hibernate;
 import java.util.List;
public class Group {

 private  String groupOp;

 private  List<Rule> rules;

 private  List<Group> groups;

/**
 * Group
 */
public Group() {
}/**
 * 条件结合
 * @param groupOp
 * @param rules
 * @param groups
 */
public Group(String groupOp, List<Rule> rules, List<Group> groups) {
    this.groupOp = groupOp;
    this.rules = rules;
    this.groups = groups;
}
public String getGroupOp(){
    return groupOp;
}


public void setGroupOp(String groupOp){
    this.groupOp = groupOp;
}


public List<Rule> getRules(){
    return rules;
}


public void setRules(List<Rule> rules){
    this.rules = rules;
}


public void setGroups(List<Group> groups){
    this.groups = groups;
}


public List<Group> getGroups(){
    return groups;
}


}