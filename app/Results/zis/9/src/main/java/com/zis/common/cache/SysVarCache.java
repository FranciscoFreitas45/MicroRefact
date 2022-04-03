package com.zis.common.cache;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.requirement.bean.SysVar;
import com.zis.requirement.repository.SysVarDao;
@Component
public class SysVarCache {

 private  Map<String,Integer> cache;

@Autowired
 private  SysVarDao sysVarDao;

public SysVarCache() {
}
public List<SysVar> getAllSysVars(){
    if (cache == null) {
        initSystemVar();
    }
    List<SysVar> list = new ArrayList<SysVar>();
    for (String key : cache.keySet()) {
        SysVar e = new SysVar();
        e.setDepKey(key);
        e.setDepValue(cache.get(key));
        list.add(e);
    }
    return list;
}


public Integer getSystemVar(String key){
    if (cache == null) {
        initSystemVar();
    }
    // 从cache中读取，如果没有，则从数据库中查询
    Integer value = cache.get(key);
    if (value == null) {
        SysVar dbVar = loadSysVarFromDB(key);
        if (dbVar != null) {
            value = dbVar.getDepValue();
            cache.put(key, dbVar.getDepValue());
        }
    }
    return value;
}


public void updateSysVar(String key,Integer value){
    if (cache == null) {
        initSystemVar();
    }
    // update db->cache
    SysVar var = loadSysVarFromDB(key);
    if (var == null) {
        var = new SysVar();
        var.setDepKey(key);
    }
    var.setDepValue(value);
    this.sysVarDao.save(var);
    cache.put(key, value);
}


public void initSystemVar(){
    // 解决方案
    cache = new ConcurrentHashMap<String, Integer>();
    // init all: db->default
    for (SysVarConstant var : SysVarConstant.values()) {
        // 加载系统变量
        SysVar sysvar = loadSysVarFromDB(var.getKeyName());
        if (sysvar == null) {
            // 如果数据库没有，保存默认值0到数据库
            sysvar = new SysVar();
            sysvar.setDepKey(var.getKeyName());
            sysvar.setDepValue(0);
            this.sysVarDao.save(sysvar);
        }
        cache.put(var.getKeyName(), sysvar.getDepValue());
    }
}


public SysVar loadSysVarFromDB(String key){
    List<SysVar> list = this.sysVarDao.findByDepKey(key);
    if (list == null || list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        throw new RuntimeException("重复的系统变量，key=" + key);
    }
    return list.get(0);
}


}