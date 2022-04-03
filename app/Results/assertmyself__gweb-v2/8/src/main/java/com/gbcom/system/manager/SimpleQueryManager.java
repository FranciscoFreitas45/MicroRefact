package com.gbcom.system.manager;
 import com.hc.core.service.HibernateService;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@SuppressWarnings("unchecked")
public class SimpleQueryManager {

@SuppressWarnings("unused")
 private  Logger logger;

@Autowired
 private  HibernateService hibernateService;


public String[] getStringArrayByHql(String hql){
    String[] ss = null;
    List list = hibernateService.findByQuery(hql);
    if (list.size() > 0) {
        Object[] objects = (Object[]) list.iterator().next();
        if (objects != null) {
            ss = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    ss[i] = objects[i].toString();
                } else {
                    ss[i] = "";
                }
            }
        }
    }
    return ss;
}


public Double getDoubleBySql(String sql){
    Double d = 0d;
    String s = getStringBySql(sql);
    if (!StringHelper.isEmpty(s)) {
        d = Double.valueOf(s);
    }
    return d;
}


public List getListBySql(String sql){
    return hibernateService.findBySql(sql);
}


public Integer getIntegerBySql(String sql){
    int d = 0;
    String s = getStringBySql(sql);
    if (!StringHelper.isEmpty(s)) {
        d = Integer.valueOf(s);
    }
    return d;
}


public Double getDoubleByHql(String hql){
    Double d = 0d;
    String s = getStringByHql(hql);
    if (!StringHelper.isEmpty(s)) {
        d = Double.valueOf(s);
    }
    return d;
}


public String getStringBySql(String sql){
    String s = null;
    List list = hibernateService.findBySql(sql);
    if (list.size() > 0) {
        Object object = list.iterator().next();
        if (object != null) {
            s = object.toString();
        }
    }
    return s;
}


public Map getMapByHql(String hql){
    Map map = new HashMap();
    List list = hibernateService.findByQuery(hql);
    for (Object object : list) {
        Object[] ss = (Object[]) object;
        if (ss != null) {
            if (ss[0] != null) {
                map.put(ss[0], ss[1] == null ? "" : ss[1]);
            }
        }
    }
    return map;
}


public Map getMapListByHql(String hql){
    Map map = new HashMap();
    List list = hibernateService.findByQuery(hql);
    for (Object object : list) {
        Object[] ss = (Object[]) object;
        if (ss != null) {
            if (ss[0] != null) {
                map.put(ss[0], ss);
            }
        }
    }
    return map;
}


public String getStringByHql(String hql){
    String s = null;
    List list = hibernateService.findByQuery(hql);
    if (list.size() > 0) {
        Object object = list.iterator().next();
        if (object != null) {
            s = object.toString();
        }
    }
    return s;
}


public List getMapList(String sql){
    return hibernateService.findBySql2Map(sql);
}


public String[] getStringArrayBySql(String sql){
    String[] ss = null;
    List list = hibernateService.findBySql(sql);
    if (list.size() > 0) {
        Object[] objects = (Object[]) list.iterator().next();
        if (objects != null) {
            ss = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                ss[i] = JspHelper.getString(objects[i]);
            }
        }
    }
    return ss;
}


public Object[] getObjectArrayByHql(String hql){
    List list = hibernateService.findByQuery(hql);
    Object[] ss = new Object[list.size()];
    for (int i = 0; i < list.size(); i++) {
        Object s = (Object) list.get(i);
        if (s != null) {
            ss[i] = s;
        }
    }
    return ss;
}


}