package com.wxcrm.common;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.danga.MemCached.MemCachedClient;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.sys.IMenuService;
import com.wxcrm.util.SysConstant;
import com.wxcrm.website.IShopAdminService;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.Interface.IMenuService;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IWeixinService;
@Service
public class MemcachedService implements IMemcachedService{

 private  Logger log;

 private  int STORE_TIME;

@Autowired
 private  MemCachedClient memCachedClient;

@Autowired
 private  IMenuService menuService;

@Autowired
 private  IAdminService adminService;

@Autowired
 private  IShopAdminService adminshopservice;

@Autowired
 private  IWeixinService weixinservice;


public void setShopAdminNameAll(List<Map<String,Object>> adminNameList){
    if (!memCachedClient.set(SysConstant.SHOP_ADMIN_NAME_ALL, adminNameList, new Date(System.currentTimeMillis() + STORE_TIME))) {
        log.error("微信商家管理员姓名存入memcached失败");
    }
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getShopAdminNameAll(){
    List<Map<String, Object>> adminNameList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.SHOP_ADMIN_NAME_ALL);
    if (adminNameList == null) {
        adminNameList = adminshopservice.queryShopAdminNameToCache();
        setShopAdminNameAll(adminNameList);
    }
    return adminNameList;
}


public void init(){
    // 清空缓存
    if (!memCachedClient.flushAll()) {
        log.error("memcached清空失败");
    }
    setMenuAll();
    log.info("总后台系统菜单存入memcached成功");
    setAdminNameAll();
    log.info("总后台管理员姓名存入memcached成功");
    setShopMenuAll();
    log.info("微信商家系统菜单存入memcached成功");
    setShopAdminNameAll();
    log.info("微信商家管理员姓名存入memcached成功");
// setDeptAll();
// log.info("系统部门存入memcached成功");
// setDeptNameAll();
// log.info("所有部门名称存入memcached成功");
// setDeptName1All();
// log.info("一级部门名称存入memcached成功");
// setDeptName2All();
// log.info("二级部门名称存入memcached成功");
// setMailConfigAll();
// log.info("邮箱配置存入memcached成功");
// setProductDatatabAll();
// log.info("产品数据表存入memcached成功");
// setProductUnitAll();
// log.info("产品数据单元存入memcached成功");
// setCompNameAll();
// log.info("装置企业名称存入memcached成功");
// setTypeNameAll();
// log.info("装置类型名称存入memcached成功");
// setInfoUnitAll();
// log.info("信息单元配置存入memcached成功");
// setNewsTempNameAll();
// log.info("新闻模板配置存入memcached成功");
// setPackUnitNameAll();
// log.info("套餐单元存入memcached成功");
// setPackInfoNameAll();
// log.info("资讯套餐名称存入memcached成功");
// setPackInfoAll();
// log.info("资讯套餐树存入memcached成功");
// setPackSmsNameAll();
// log.info("短信套餐名称存入memcached成功");
// setPackSmsAll();
// log.info("短信套餐树存入memcached成功");
// setPackLeafSmsNameAll();
// log.info("短信基础套餐名称存入memcached成功");
// setPackLeafInfoNameAll();
// log.info("资讯基础套餐名称存入memcached成功");
// 
// setPackDxtAll();
// log.info("短讯通套餐树存入memcached成功");
// setPackDxtNameAll();
// log.info("短讯通套餐名称存入memcached成功");
// setPackLeafDxtNameAll();
// log.info("短讯通基础套餐名称存入memcached成功");
// 
// setPackMeetingAll();
// log.info("会议套餐树存入memcached成功");
// setPackMeetingNameAll();
// log.info("会议套餐名称存入memcached成功");
// setPackLeafMeetingNameAll();
// log.info("会议基础套餐名称存入memcached成功");
// 
// setPackWeiAll();
// log.info("微信套餐树存入memcached成功");
// setPackWeiNameAll();
// log.info("微信套餐名称存入memcached成功");
// setPackLeafWeiNameAll();
// log.info("微信基础套餐名称存入memcached成功");
// 
// setPackPrtAll();
// log.info("印刷品套餐树存入memcached成功");
// setPackPrtNameAll();
// log.info("印刷品套餐名称存入memcached成功");
// setPackLeafPrtNameAll();
// log.info("印刷品基础套餐名称存入memcached成功");
// 
// 
// setPackLeafAdvNameAll();
// log.info("广告基础套餐名称存入memcached成功");
// setPackAdvNameAll();
// log.info("广告套餐名称存入memcached成功");
// setPackAdvAll();
// log.info("广告套餐树存入memcached成功");
// setBlacktelAll();
// log.info("黑名单存入memcached成功");
}


public void setShopMenuAll(List<Map<String,Object>> menuList){
    if (!memCachedClient.set(SysConstant.SHOP_MENU_ALL, menuList, new Date(System.currentTimeMillis() + STORE_TIME))) {
        log.error("微信商家系统菜单存入memcached失败");
    }
}


public void setMenuAll(List<Map<String,Object>> menuList){
    if (!memCachedClient.set(SysConstant.MENU_ALL, menuList, new Date(System.currentTimeMillis() + STORE_TIME))) {
        log.error("系统菜单存入memcached失败");
    }
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getShopMenuAll(){
    List<Map<String, Object>> menuList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.SHOP_MENU_ALL);
    if (menuList == null) {
        menuList = menuService.queryShopMenuToCache();
        setShopMenuAll(menuList);
    }
    return menuList;
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getAdminNameAll(){
    List<Map<String, Object>> adminNameList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.ADMIN_NAME_ALL);
    if (adminNameList == null) {
        adminNameList = adminService.queryAdminNameToCache();
        setAdminNameAll(adminNameList);
    }
    return adminNameList;
}


public void setAdminNameAll(List<Map<String,Object>> adminNameList){
    if (!memCachedClient.set(SysConstant.ADMIN_NAME_ALL, adminNameList, new Date(System.currentTimeMillis() + STORE_TIME))) {
        log.error("管理员姓名存入memcached失败");
    }
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getMenuAll(){
    List<Map<String, Object>> menuList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.MENU_ALL);
    if (menuList == null) {
        menuList = menuService.queryMenuToCache();
        setMenuAll(menuList);
    }
    return menuList;
}


}