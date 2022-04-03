package com.gbcom.common.im.IMVersionChain;
 import com.gbcom.common.im.exception.IllegalCmArgumentException;
import java.io.Serializable;
import java.util.HashMap;
public class IMItem implements Serializable{

 private  long serialVersionUID;

 private  String version;

 private  IIM defaultIM;

/**
 * 构造函数
 *
 * @param version
 *            版本号
 * @param defaultIM
 *            对应的信息模型
 */
protected IMItem(String version, IIM defaultIM) {
    this.version = version;
    this.defaultIM = defaultIM;
}/**
 * 构造函数
 *
 * @param version
 *            版本号
 */
protected IMItem(String version) {
    this.version = version;
}
public IIM getIM(){
    return this.defaultIM;
}


public void setIM(IIM im){
    this.defaultIM = im;
}


}