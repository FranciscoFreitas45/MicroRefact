package org.jeecgframework.core.extend.template;
 import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
@SuppressWarnings("unchecked")
public class DataSourceMap {

 private  Map<Object,Object> dsm;


public Map<Object,Object> getDataSourceMap(){
    Map<Object, Object> datadsm = new HashMap<Object, Object>();
    datadsm.putAll(dsm);
    return datadsm;
}


}