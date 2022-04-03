package com.gbcom.common.im;
 import com.gbcom.common.im.exception.IllegalCmArgumentException;
import java.io.Serializable;
import java.util.HashMap;
public class IMVersionChain implements Serializable{

 private  long serialVersionUID;

 private  String type;

 private  HashMap<String,IMItem> itemMap;

 private  long serialVersionUID;

 private  String version;

 private  IIM defaultIM;

/**
 * 构造函数，必须传入网元类型
 *
 * @param type String
 */
public IMVersionChain(String type) {
    this.type = type;
}
public IIM getIM(){
    return this.defaultIM;
}


public void dropItem(String version){
    itemMap.remove(version);
}


public void putIm(String version,IIM iim){
    if (itemMap.get(version) == null) {
        IMItem item = new IMItem(version);
        item.setIM(iim);
        itemMap.put(version, item);
        return;
    }
    itemMap.get(version).setIM(iim);
}


public boolean contains(int cid){
    // 获取所有模型版本信息列表
    String[] vers = this.getVersionList();
    if (vers == null) {
        return false;
    }
    for (int i = 0; i < vers.length; i++) {
        IIM tempIM = this.getIM(vers[i]);
        if (tempIM != null && tempIM.getClassDesc(cid) != null) {
            return true;
        }
    }
    return false;
}


public void setIM(IIM im){
    this.defaultIM = im;
}


public String[] getVersionList(){
    return this.itemMap.keySet().toArray(new String[0]);
}


public String getType(){
    return this.type;
}


public IMItem[] getAllIM(){
    return this.itemMap.values().toArray(new IMItem[0]);
}


}