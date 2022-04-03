package com.gbcom.common.im.desc;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EnumAttributeDesc extends AttributeDescimplements IEnumAttribute{

 private  long serialVersionUID;

 private  Logger logger;

 private  ArrayList<Entry> entries;

 private  HashMap<Integer,String> groups;

 private  long serialVersionUID;

 private  int groupID;

 private  String name;

 private  int value;

 private  String uIDisplay;

/**
 */
public EnumAttributeDesc() {
    super();
}/**
 * @param attrID int
 * @param name String
 * @param enumValue int[]
 * @param enumName String[]
 */
public EnumAttributeDesc(int attrID, String name, int[] enumValue, String[] enumName) {
    super();
    setAliasName(name);
    setAttrID(attrID);
    // ComboBox Editor
    setControl("6");
    setData("5");
    setDefault("");
    setLength("2");
    setMask("56");
    setName(name);
    for (int i = 0; i < enumValue.length && i < enumName.length; i++) {
        addEntry(0, "enum", "", enumValue[i], enumName[i]);
    }
}
@Override
public int getGroupValue(int groupId,int value){
    return this.getGroupValue(groupId, value, true);
}


@Override
public String[] getAllDis(int groupId){
    int length = this.entries.size();
    ArrayList<String> temp = new ArrayList<String>();
    for (int i = 0; i < length; i++) {
        if (this.entries.get(i).groupID == groupId) {
            temp.add(this.entries.get(i).uIDisplay);
        }
    }
    return temp.toArray(new String[temp.size()]);
}


@Override
public int[] getAllGroup(){
    if (this.groups.isEmpty()) {
        return null;
    }
    int[] result = new int[this.groups.keySet().size()];
    int i = 0;
    Iterator<Integer> it = this.groups.keySet().iterator();
    while (it.hasNext()) {
        result[i++] = it.next().intValue();
    }
    // 增加排序后返回
    int temp;
    for (int j = 0; j < result.length; j++) {
        for (int k = 0; k < result.length; k++) {
            if (result[j] < result[k]) {
                temp = result[j];
                result[j] = result[k];
                result[k] = temp;
            }
        }
    }
    return result;
}


public void removeEntries(int[] groupIds){
    ArrayList<Entry> entries = new ArrayList<Entry>();
    int length = this.entries.size();
    for (int i = 0; i < length; ) {
        Entry e = this.entries.get(i);
        int j = 0;
        for (; j < groupIds.length; j++) {
            if (e.groupID == groupIds[j]) {
                entries.remove(e);
                break;
            }
        }
        if (j == groupIds.length) {
            i++;
        }
    }
}


@Override
public String getEnumName(int groupId,int value){
    return this.getEnumName(groupId, value, true);
}


@Override
public int getEnumValue(int groupId,String dis){
    int length = this.entries.size();
    for (int i = 0; i < length; i++) {
        Entry e = this.entries.get(i);
        if (e.groupID == groupId && e.uIDisplay.equals(dis)) {
            return e.value;
        }
    }
    // 主要是针对存在可以自行输入值的一些配置项，
    return Integer.parseInt(dis);
// return -1;
}


public void removeAllEntries(){
    groups.clear();
    entries.clear();
}


@Override
public String getGroupName(int groupId){
    if (this.groups.containsKey(Integer.valueOf(groupId))) {
        return this.groups.get(Integer.valueOf(groupId));
    }
    return null;
}


public int getValue(){
    return value;
}


public void addEntry(int groupID,String groupName,String attrName,int attrValue,String enumName){
    try {
        if (!groups.containsValue(groupName)) {
            groups.put(Integer.valueOf(groupID), groupName);
        }
        Entry newEntry = new Entry(groupID, attrName, attrValue, enumName);
        entries.add(newEntry);
    } catch (NumberFormatException e) {
        logger.error("EnumAttributeDesc.addEntry", e);
    }
}


public ArrayList<Entry> getEntries(int groupId){
    ArrayList<Entry> entries = new ArrayList<Entry>();
    int length = this.entries.size();
    for (int i = 0; i < length; i++) {
        Entry e = this.entries.get(i);
        if (e.groupID == groupId) {
            entries.add(e);
        }
    }
    return entries;
}


@Override
public AttributeDesc clone(){
    EnumAttributeDesc attrDescClone = new EnumAttributeDesc();
    attrDescClone.aid = this.aid;
    attrDescClone.aliasName = this.aliasName;
    attrDescClone.attrName = this.attrName;
    attrDescClone.controlType = this.controlType;
    attrDescClone.defaultValue = this.defaultValue;
    attrDescClone.dataType = this.dataType;
    attrDescClone.length = this.length;
    attrDescClone.mask = this.mask;
    attrDescClone.maxValue = this.maxValue;
    attrDescClone.minValue = this.minValue;
    attrDescClone.atCmd = this.atCmd;
    attrDescClone.rspParser = this.rspParser;
    attrDescClone.entries = (ArrayList<Entry>) this.entries.clone();
    attrDescClone.groups = (HashMap<Integer, String>) this.groups.clone();
    return attrDescClone;
}


public void addGroup(String group,String groupName){
    try {
        int groupID = Integer.parseInt(group);
        if (!groups.containsValue(groupName)) {
            groups.put(Integer.valueOf(groupID), groupName);
        }
    } catch (NumberFormatException e) {
        logger.error("EnumAttributeDesc.addGroup", e);
    }
}


public Integer[] getAllEnumVars(){
    int length = this.entries.size();
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for (int i = 0; i < length; i++) {
        temp.add(this.entries.get(i).value);
    }
    return temp.toArray(new Integer[temp.size()]);
}


public String getUIDisplay(){
    return uIDisplay;
}


}