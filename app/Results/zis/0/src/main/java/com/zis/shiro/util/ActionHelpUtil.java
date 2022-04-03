package com.zis.shiro.util;
 import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.zis.shiro.bean.Permission;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.service.RegistAndUpdateService;
@Controller
public class ActionHelpUtil {

 protected  String BOOK_INFO;

 protected  String PURCHASE;

 protected  String REQUIREMENT;

 protected  String TOOLKIT;

 protected  String SHIRO;

 protected  String STOCK;

 protected  String DATA;

 protected  String ORDER;

 protected  String SHOP;

@Autowired
 private  RegistAndUpdateService registAndUpdateService;


public List<String> groupNames(){
    List<String> list = new ArrayList<String>();
    list.add(BOOK_INFO);
    list.add(PURCHASE);
    list.add(REQUIREMENT);
    list.add(TOOLKIT);
    list.add(SHIRO);
    list.add(STOCK);
    list.add(DATA);
    list.add(ORDER);
    list.add(SHOP);
    return list;
}


public List<Permission> getGroupList(List<Permission> list,String GroupName){
    List<Permission> listPermissions = new ArrayList<Permission>();
    for (Permission p : list) {
        if (p.getGroupName().equals(GroupName)) {
            listPermissions.add(p);
        }
    }
    return listPermissions;
}


public ActiveUser getActiveUser(){
    Subject my = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) my.getPrincipals().getPrimaryPrincipal();
    return au;
}


public List<Integer> getPermissionIds(List<Permission> list){
    List<Integer> PermissionIds = new ArrayList<Integer>();
    for (Permission p : list) {
        PermissionIds.add(p.getId());
    }
    return PermissionIds;
}


public List<Permission> getUserPermissions(){
    List<Permission> pList = getActiveUser().getPermissions();
    return pList;
}


public void putPermissionToView(ModelMap map){
    map.put("registList", this.registAndUpdateService.getGroupPermissions(SHIRO));
    map.put("purchaseList", this.registAndUpdateService.getGroupPermissions(PURCHASE));
    map.put("requirementList", this.registAndUpdateService.getGroupPermissions(REQUIREMENT));
    map.put("bookInfoList", this.registAndUpdateService.getGroupPermissions(BOOK_INFO));
    map.put("toolkitList", this.registAndUpdateService.getGroupPermissions(TOOLKIT));
    map.put("stockList", this.registAndUpdateService.getGroupPermissions(STOCK));
    map.put("dataList", this.registAndUpdateService.getGroupPermissions(DATA));
    map.put("orderList", this.registAndUpdateService.getGroupPermissions(ORDER));
    map.put("shopList", this.registAndUpdateService.getGroupPermissions(SHOP));
}


}