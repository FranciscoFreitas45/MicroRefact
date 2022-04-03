package com.gbcom.DTO;
 import com.gbcom.system.daoservice;
import com.gbcom.system.domain;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserService;
public class SysAreaManager {

 private  Logger logger;

 private  SysUserManager sysUserManager;

 private  SysUserService sysUserService;

 private  SysAreaService sysAreaService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


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


public SysUser getSysUser(String loginName){
    List<SysUser> list = sysUserService.findByProperty("loginName", loginName);
    if (list.size() > 0) {
        return list.iterator().next();
    }
    return null;
}


public Set<SysAreaNes> getAreaNes(){
    return getAreaNes(getSysArea());
}


public List<String> sysAreaHotsUnderSysArea(Long areaId){
    SysArea area = getSysArea(areaId);
    return sysAreaHotsUnderSysArea(area);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sysAreaHotsUnderSysArea"))

.queryParam("areaId",areaId)
;
List<String> aux = restTemplate.getForObject(builder.toUriString(),List<String>.class);
return aux;
}


}