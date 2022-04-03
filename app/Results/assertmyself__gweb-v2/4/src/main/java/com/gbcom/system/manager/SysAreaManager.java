package com.gbcom.system.manager;
 import com.gbcom.system.daoservice;
import com.gbcom.system.domain;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserService;
@Service
public class SysAreaManager {

 private  Logger logger;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysUserService sysUserService;

@Autowired
 private  SysAreaService sysAreaService;


@Override
public int compare(String arg1,String arg2){
    return arg1.compareToIgnoreCase(arg2);
}


public boolean isGroupView(){
    return sysAreaService.findAll().size() != 0;
}


public List<String> sysAreaHotsUnderSysArea(Long areaId){
    SysArea area = getSysArea(areaId);
    return sysAreaHotsUnderSysArea(area);
}


public List<SysArea> getChild(SysArea area){
    String hql = "from SysArea where parent.id=" + area.getId() + " order by id asc";
    List<SysArea> nodeList = sysAreaService.findByQuery(hql);
    return nodeList;
}


public Set<SysAreaNes> getAllAreaNes(){
    // return new HashSet<SysAreaNes>(sysAreaNesService.findAll());
    Set<SysAreaNes> sets = new HashSet<SysAreaNes>();
    List<SysArea> list = new ArrayList<SysArea>();
    findLeafChild(null, list);
    for (SysArea each : list) {
        sets.addAll(each.getNes());
    }
    return sets;
}


public SysArea getSysArea(){
    return sysUserManager.getSysUser().getArea();
}


public List<String> allSysAreaNeIDs(){
    List<String> list = new ArrayList<String>();
    for (SysAreaNes ne : getAllAreaNes()) {
        if (ne != null) {
            list.add(ne.getNeID());
        }
    }
    return list;
}


public List<SysArea> findByLayer(Long layer){
    return sysAreaService.findByLayer(layer);
}


public String sysAreaNames(){
    return sysAreaNeNames(getSysArea());
}


public SysUser getSysUser(String loginName){
    List<SysUser> list = sysUserService.findByProperty("loginName", loginName);
    if (list.size() > 0) {
        return list.iterator().next();
    }
    return null;
}


public List<String> sysAreaHots(SysArea area){
    List<String> list = new ArrayList<String>();
    for (SysAreaNes ne : area.getNes()) {
        if (ne != null) {
            try {
                list.add(ne.getNeID());
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }
    Collections.sort(list, new Comparator<String>() {

        @Override
        public int compare(String arg1, String arg2) {
            return arg1.compareToIgnoreCase(arg2);
        }
    });
    return list;
}


public Set<SysAreaNes> getAreaNes(){
    return getAreaNes(getSysArea());
}


public String sysAreaNeNames(SysArea area){
    String keys = "";
    for (SysAreaNes ne : area.getNes()) {
        if (ne != null) {
        }
        keys += ne.getNeName() + ",";
    }
    return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(",")) : "";
}


public void findLeafChild(SysArea area,List<SysArea> list){
    try {
        if (area == null) {
            for (SysArea each : findByLayer(1L)) {
                findLeafChild(each, list);
            }
            return;
        }
        if (area.getIsLeaf()) {
            list.add(area);
        } else {
            for (SysArea each : getChild(area)) {
                if (each != null) {
                    findLeafChild(each, list);
                }
            }
        }
    } catch (Exception e) {
        logger.error("find leaf child failed!!! " + e);
    }
}


}