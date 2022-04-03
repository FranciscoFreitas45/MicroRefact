package com.gbcom.common.im;
 import com.gbcom.common.im.desc.IClassDesc;
import com.gbcom.common.im.ds.IDArray;
import com.gbcom.common.im.ds.IIDNameStruct;
import com.gbcom.common.im.ds.NameArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class DefaultIM implements IIM{

 private  long serialVersionUID;

 private  String version;

 private  String type;

 private  IClassDesc root;

 private  long timeStamp;

 private  IDArray classIdMap;

 private  NameArray classNameMap;

 private  Map<String,IClassDesc> tableNameMap;

/**
 * 构造函数
 *
 * @param type
 *            该信息模型的根类型
 * @param ver
 *            该信息模型的版本
 * @param root
 *            该信息模型的根类描述
 * @param data
 *            该信息模型包含的所有类描述
 * @param timeStamp
 *            该信息模型对应的文件的时间戳
 */
public DefaultIM(String type, String ver, IClassDesc root, ArrayList<IIDNameStruct> data, long timeStamp) {
    this.version = ver;
    this.type = type;
    this.root = root;
    this.classIdMap = new IDArray(data);
    this.classNameMap = new NameArray(data);
    this.tableNameMap = new HashMap<String, IClassDesc>();
    for (IIDNameStruct struct : data) {
        IClassDesc desc = (IClassDesc) struct;
    // ...
    }
    this.timeStamp = timeStamp;
}
@Override
public String getVersion(){
    return this.version;
}


@Override
public IClassDesc getClassDesc(String classDesc){
    return (IClassDesc) classNameMap.findFirst(classDesc);
}


@Override
public long getTimeStamp(){
    return this.timeStamp;
}


@Override
public IClassDesc getClassDescByTableName(String snmpTableName){
    return tableNameMap.get(snmpTableName);
}


public void addClassDesc(IClassDesc classDesc){
    classIdMap.put((IIDNameStruct) classDesc);
    classNameMap.put((IIDNameStruct) classDesc);
    if (tableNameMap == null) {
        tableNameMap = new HashMap<String, IClassDesc>();
    }
}


@Override
public String getType(){
    return this.type;
}


public IClassDesc[] getAllClassDescs(){
    IIDNameStruct[] ids = classIdMap.get();
    IClassDesc[] classes = new IClassDesc[ids.length];
    for (int i = 0; i < ids.length; i++) {
        classes[i] = (IClassDesc) ids[i];
    }
    return classes;
}


@Override
public String toString(){
    // print out all classes
    StringBuffer buf = new StringBuffer();
    // vm info
    buf.append("\n #################IIM type:" + this.getType());
    buf.append("#####Version:" + this.getVersion());
    buf.append("#####timeStamp:" + new Date(this.getTimeStamp()) + "#################");
    // detail info
    IClassDesc[] classes = this.getAllClassDescs();
    for (int i = 0; i < classes.length; i++) {
        buf.append("\n " + ((IClassDesc) classes[i]).toString());
    }
    return buf.toString();
}


@Override
public IClassDesc getRoot(){
    return this.root;
}


}