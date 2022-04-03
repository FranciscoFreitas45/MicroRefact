package cn.maxcj.core.common.constant.dictmap.base;
 import java.util.HashMap;
public class AbstractDictMap {

 protected  HashMap<String,String> dictory;

 protected  HashMap<String,String> fieldWarpperDictory;

public AbstractDictMap() {
    put("id", "主键id");
    init();
    initBeWrapped();
}
public void init()


public String getFieldWarpperMethodName(String key){
    return this.fieldWarpperDictory.get(key);
}


public void initBeWrapped()


public String get(String key){
    return this.dictory.get(key);
}


public void putFieldWrapperMethodName(String key,String methodName){
    this.fieldWarpperDictory.put(key, methodName);
}


public void put(String key,String value){
    this.dictory.put(key, value);
}


}