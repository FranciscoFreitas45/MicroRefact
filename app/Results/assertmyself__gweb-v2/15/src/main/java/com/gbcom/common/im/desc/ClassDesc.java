package com.gbcom.common.im.desc;
 import java.lang.reflect.Array;
import java.util.ArrayList;
import com.gbcom.common.im.ds.IDNameStructUtil;
import com.gbcom.common.im.parse.alarm.EmptyAlarmParser;
import com.gbcom.common.im.parse.alarm.IAlarmParser;
public class ClassDesc implements IClassDesc,Cloneable{

 private  long serialVersionUID;

 private  IClassDesc[] children;

 private  IClassDesc parent;

 private  IAttributeDesc[] attributes;

 private  String className;

 private  String aliasName;

 private  boolean canBatchOper;

 private  String alarmParserClassName;

 private  int classID;

 private  IAlarmParser alarmParser;

 private  IOperationDesc[] operationDescs;

 private  String responseParserClassName;

 private  boolean vector;

 private  int maxIndex;

 private  int mask;

/**
 * 构造函数
 *
 * @param classID
 *            类ID
 * @param className
 *            类名
 */
public ClassDesc(String classID, String className) {
    try {
        this.classID = Integer.decode(classID).intValue();
        this.className = className;
    } catch (Exception e) {
        e.printStackTrace();
    }
}/**
 * 构造方法
 *
 * @param classID int
 * @param className String
 */
public ClassDesc(int classID, String className) {
    try {
        this.classID = classID;
        this.className = className;
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@Override
public String getName(){
    return this.className;
}


public void setResponseParserClassName(String responseParserClassName){
    this.responseParserClassName = responseParserClassName;
}


@Override
public IAttributeDesc[] getFriendAttributeDescs(){
    if (attributes == null) {
        return null;
    }
    ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
    for (int i = 0; i < attributes.length; i++) {
        if (attributes[i].isFriendlyName()) {
            tempAL.add(attributes[i]);
        }
    }
    if (!tempAL.isEmpty()) {
        return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
    }
    return null;
}


@Override
public boolean isAvaliableInCreate(){
    // 3位：是否是创建对象时的不可见属性
    return (this.mask & IS_AVAILABLE_IN_CREATE_MASK) != 0;
}


public void setCanBatchOper(boolean canBatchOper){
    this.canBatchOper = canBatchOper;
}


@Override
public IAttributeDesc[] getKeyAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
    for (int i = 0; i < attributes.length; i++) {
        if (attributes[i].isKey()) {
            tempAL.add(attributes[i]);
        }
    }
    if (!tempAL.isEmpty()) {
        return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
    }
    return null;
}


public boolean isVector(){
    return vector;
}


@Override
public IAttributeDesc[] getAttributeDescs(){
    return attributes;
}


public int getAttributeDescIndex(int aid){
    return IDNameStructUtil.binarySearch(this.attributes, aid);
}


public void serAlarmParser(IAlarmParser alarmParser){
    this.alarmParser = alarmParser;
}


@Override
public IAttributeDesc[] getNonMOCAttributeDescs(){
    if (attributes == null) {
        return null;
    }
    ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
    for (int i = 0; i < attributes.length; i++) {
        if (!attributes[i].isOMC()) {
            tempAL.add(attributes[i]);
        }
    }
    if (!tempAL.isEmpty()) {
        return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
    }
    return null;
}


public void setAttrs(IAttributeDesc[] attrDescs){
    this.attributes = (IAttributeDesc[]) IDNameStructUtil.sort(attrDescs);
}


@Override
public IAttributeDesc[] getSkeletonAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    ArrayList<IAttributeDesc> tempAL = new ArrayList<IAttributeDesc>();
    for (int i = 0; i < attributes.length; i++) {
        if (attributes[i].isSKTNeeded()) {
            tempAL.add(attributes[i]);
        }
    }
    if (!tempAL.isEmpty()) {
        return tempAL.toArray(new IAttributeDesc[tempAL.size()]);
    }
    return null;
}


@Override
public IAttributeDesc getRunStatusAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    for (int i = attributes.length - 1; i >= 0; i--) {
        if (attributes[i].isRunStatus()) {
            return attributes[i];
        }
    }
    return null;
}


public void setParent(IClassDesc parent){
    this.parent = parent;
}


@Override
public IAttributeDesc getManageStatusAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    for (int i = attributes.length - 1; i >= 0; i--) {
        if (attributes[i].isManageStatus()) {
            return attributes[i];
        }
    }
    return null;
}


@Override
public boolean isAvaliableInModify(){
    // 4位：是否是修改对象时的不可见属性
    return (this.mask & IS_AVAILABLE_IN_MODIFY_MASK) != 0;
}


public String getResponseParserClassName(){
    return responseParserClassName;
}


public void setAlarmParserName(String alarmParserClassName){
    this.alarmParserClassName = alarmParserClassName;
}


@Override
public IAlarmParser getAlarmParser(){
    if (this.alarmParser != null) {
        return this.alarmParser;
    }
    try {
        IAlarmParser alarmParser = (IAlarmParser) this.getClass().getClassLoader().loadClass(this.alarmParserClassName).newInstance();
        return alarmParser;
    } catch (Exception e) {
        // 如果不存在触发器，则分配一个空触发器，避免每次触发器为空时都去加载。
        this.alarmParser = new EmptyAlarmParser();
        return this.alarmParser;
    }
}


@Override
public String getVersion(){
    return this.parent.getVersion();
}


public int getMask(){
    return mask;
}


@Override
public IClassDesc getParent(){
    return this.parent;
}


public boolean isCanBatchOper(){
    return canBatchOper;
}


public void setMask(int mask){
    this.mask = mask;
}


public void setVector(boolean vector){
    this.vector = vector;
}


public void setAliasName(String aliasName){
    this.aliasName = aliasName;
}


@Override
public IClassDesc getChild(String className){
    if (children != null) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].getName().equals(className)) {
                return children[i];
            }
        }
    }
    return null;
}


@Override
public int getID(){
    return this.classID;
}


public IAttributeDesc getAttributeDescByIndex(int index){
    if (this.attributes == null) {
        return null;
    }
    if (this.attributes.length > index) {
        return this.attributes[index];
    }
    throw new ArrayIndexOutOfBoundsException("index:" + index + " is out of AtrributeDesc length:" + this.attributes.length);
}


@Override
public IAttributeDesc getAttributeDesc(String attributeName){
    if (attributes == null || attributeName == null) {
        return null;
    }
    for (int i = 0; i < attributes.length; i++) {
        if (attributes[i].getName().equals(attributeName)) {
            return attributes[i];
        }
    }
    return null;
}


@Override
public int getMaxIndex(){
    // TODO Auto-generated method stub
    return maxIndex;
}


@Override
public IClassDesc[] getChildren(){
    return this.children;
}


@Override
public String getUIName(){
    return this.aliasName;
}


public void addAttr(IAttributeDesc attrDesc){
    this.attributes = (IAttributeDesc[]) IDNameStructUtil.add(attributes, attrDesc);
}


@Override
public String getType(){
    return this.parent.getType();
}


@Override
public String getAlarmParserClassName(){
    return alarmParserClassName;
}


@Override
public Object clone(){
    ClassDesc cloneCD = new ClassDesc(this.classID, this.className);
    cloneCD.children = this.children;
    cloneCD.parent = this.parent;
    cloneCD.setAttrs(this.getAttributeDescs());
    cloneCD.setAliasName(this.getUIName());
    cloneCD.alarmParserClassName = this.alarmParserClassName;
    cloneCD.alarmParser = this.alarmParser;
    return cloneCD;
}


@Override
public String toString(){
    StringBuffer buf = new StringBuffer();
    buf.append("\n----------------------------");
    buf.append("\nClass ClassCid: " + this.getID());
    if (this.getParent() != null) {
        buf.append("\nclass parent's id: " + this.getParent().getID());
    } else {
        buf.append("\nclass parent: " + "null");
    }
    buf.append("\nClass name: " + this.getName());
    buf.append("\nClass UI name: " + this.getUIName());
    buf.append("\n\nAttributes :");
    IAttributeDesc[] descs = (IAttributeDesc[]) this.getAttributeDescs();
    if (descs != null) {
        for (int i = 0; i < descs.length; i++) {
            buf.append((IAttributeDesc) descs[i]).toString();
        }
    }
    buf.append("\n----------------------------");
    return buf.toString();
}


public void addOperationDescs(IOperationDesc operationDesc){
    if (this.operationDescs == null || this.operationDescs.length < 1) {
        this.operationDescs = this.operationDescs == null ? (IOperationDesc[]) Array.newInstance(operationDesc.getClass(), 1) : (IOperationDesc[]) Array.newInstance(this.operationDescs.getClass().getComponentType(), 1);
        this.operationDescs[0] = operationDesc;
        return;
    }
    for (int i = 0; i < this.operationDescs.length; i++) {
        if (this.operationDescs[i].equals(operationDesc)) {
            return;
        }
    }
    IOperationDesc[] result = null;
    result = (IOperationDesc[]) Array.newInstance(IOperationDesc.class, this.operationDescs.length + 1);
    System.arraycopy(this.operationDescs, 0, result, 0, this.operationDescs.length);
    result[result.length - 1] = operationDesc;
    this.operationDescs = result;
}


@Override
public IAttributeDesc getFriendAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    for (int i = attributes.length - 1; i >= 0; i--) {
        if (attributes[i].isFriendlyName()) {
            return attributes[i];
        }
    }
    return null;
}


@Override
public void setMaxIndex(int max){
    this.maxIndex = max;
}


@Override
public IAttributeDesc getRunDetailStatusAttributeDesc(){
    if (attributes == null) {
        return null;
    }
    for (int i = attributes.length - 1; i >= 0; i--) {
        if (attributes[i].isRunDetailStatus()) {
            return attributes[i];
        }
    }
    return null;
}


public void addChild(IClassDesc child){
    this.children = (IClassDesc[]) IDNameStructUtil.add(children, child);
}


public void setChildren(IClassDesc[] children){
    this.children = (IClassDesc[]) IDNameStructUtil.sort(children);
}


}