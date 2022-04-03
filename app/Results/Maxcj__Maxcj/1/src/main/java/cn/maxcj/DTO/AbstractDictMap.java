package cn.maxcj.DTO;
 import java.util.HashMap;
public class AbstractDictMap {

 protected  HashMap<String,String> dictory;

 protected  HashMap<String,String> fieldWarpperDictory;

public AbstractDictMap() {
    put("id", "主键id");
    init();
    initBeWrapped();
}
public String getFieldWarpperMethodName(String key){
    return this.fieldWarpperDictory.get(key);
}


public String get(String key){
    return this.dictory.get(key);
}


}