package com.gbcom.common.im.JointManager;
 import java.lang.reflect.Array;
import java.util.ArrayList;
import com.gbcom.common.im.desc.ClassDesc;
import org.apache.log4j.Logger;
public class JointItem {

 final  String type;

 private  String version;

 private  int classId;

 private  String[] childNEType;

/**
 * 构造函数
 *
 * @param type
 *            网元类型
 * @param version
 *            网元版本
 * @param classId
 *            类ID
 */
public JointItem(String type, String version, int classId) {
    this.type = type;
    this.version = version;
    this.classId = classId;
}
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


public String[] getChildNEType(){
    return childNEType;
}


}