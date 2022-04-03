package com.gbcom.common.im;
 import java.lang.reflect.Array;
import java.util.ArrayList;
import com.gbcom.common.im.desc.ClassDesc;
import org.apache.log4j.Logger;
public class JointManager {

 private  ArrayList<JointItem> imJointList;

 private  Logger log;

 final  String type;

 private  String version;

 private  int classId;

 private  String[] childNEType;


public String getVersion(){
    return version;
}


public void addChildNeType(String childType){
    if (this.childNEType == null || this.childNEType.length < 1) {
        this.childNEType = this.childNEType == null ? (String[]) Array.newInstance(childType.getClass(), 1) : (String[]) Array.newInstance(this.childNEType.getClass().getComponentType(), 1);
        this.childNEType[0] = childType;
        return;
    }
    for (int i = 0; i < this.childNEType.length; i++) {
        if (this.childNEType[i].equals(childType)) {
            return;
        }
    }
    String[] result = null;
    result = (String[]) Array.newInstance(this.childNEType.getClass().getComponentType(), this.childNEType.length + 1);
    System.arraycopy(this.childNEType, 0, result, 0, this.childNEType.length);
    result[result.length - 1] = childType;
    this.childNEType = result;
}


public int getClassId(){
    return classId;
}


public String getType(){
    return type;
}


public int hashCode(){
    return new HashCodeBuilder().append(this.type).append(this.version).append(this.classId).toHashCode();
}


public boolean equals(Object obj){
    if (obj instanceof JointItem) {
        JointItem item = (JointItem) obj;
        if (this.type == null || this.version == null) {
            return false;
        }
        if (this.type.equals(item.getType()) && this.version.equals(item.getVersion()) && this.classId == item.getClassId()) {
            return true;
        }
        return false;
    } else {
        return false;
    }
}


public void startJoint(){
    if (imJointList != null) {
        for (JointItem item : imJointList) {
            ClassDesc classDesc = (ClassDesc) IMSvrService.getInstance().getIIM(item.getType(), item.getVersion()).getClassDesc(item.classId);
            String[] childNeTypes = item.getChildNEType();
            if (childNeTypes != null && classDesc != null) {
                for (String childNeType : childNeTypes) {
                    try {
                        IMVersionChain versionChain = IMSvrService.getInstance().getIMChain(childNeType);
                        if (versionChain != null) {
                            IMVersionChain.IMItem[] vimItems = versionChain.getAllIM();
                            if (vimItems != null) {
                                for (IMVersionChain.IMItem vimItem : vimItems) {
                                    classDesc.addChild(vimItem.getIM().getRoot());
                                    ((ClassDesc) vimItem.getIM().getRoot()).setParent(classDesc);
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.warn("can't find ChildNe Type " + childNeType + " at ClassDesc:" + classDesc.getID() + " when joint IM");
                    }
                }
            }
        }
    }
    // 联接完成后把内存中的联接信息删除
    imJointList = null;
}


public JointItem getJointItem(String type,String version,int classId){
    return new JointItem(type, version, classId);
}


public String[] getChildNEType(){
    return childNEType;
}


public void putIMJointInfo(String type,String version,int classId,String childNeType){
    if (imJointList == null) {
        imJointList = new ArrayList<JointItem>();
    }
    JointItem tmp = getJointItem(type, version, classId);
    if (imJointList.contains(tmp)) {
        for (JointItem item : imJointList) {
            if (item.equals(tmp)) {
                item.addChildNeType(childNeType);
            }
        }
    } else {
        tmp.addChildNeType(childNeType);
        imJointList.add(tmp);
    }
}


}