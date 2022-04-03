package com.gbcom.common.im.desc.EnumAttributeDesc;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Entry implements Serializable{

 private  long serialVersionUID;

 private  int groupID;

 private  String name;

 private  int value;

 private  String uIDisplay;

/**
 * 构造函数
 * @param name String
 * @param groupId
 *            对应的groupId
 * @param value
 *            对应的属性值
 * @param enumName
 *            对应的界面显示
 */
protected Entry(int groupId, String name, int value, String enumName) {
    this.groupID = groupId;
    this.name = name;
    this.value = value;
    this.uIDisplay = enumName;
}
public int getValue(){
    return value;
}


public String getUIDisplay(){
    return uIDisplay;
}


}