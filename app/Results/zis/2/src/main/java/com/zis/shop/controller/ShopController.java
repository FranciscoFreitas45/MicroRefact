package com.zis.shop.controller;
 import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.SaveOrUpdateShopDto;
import com.zis.shop.service.ShopService;
import com.zis.DTO.ActiveUser;
@Controller
@RequestMapping(value = "/shop")
public class ShopController {

 private  Logger logger;

 private  String OPERATE_TYPE_UPDATE;

 private  String OPERATE_TYPE_ADD;

 private  String P_NAME_TAOBAO;

 private  String P_NAME_YOUZAN;

@Autowired
 private  ShopService shopService;


@RequestMapping(value = "/gotoSaveShop")
public String gotoSaveShop(ModelMap map){
    SaveOrUpdateShopDto dto = new SaveOrUpdateShopDto();
    dto.setTypeStatus(OPERATE_TYPE_ADD);
    map.put("shop", dto);
    return "shop/shop/saveOrUpdate/save-or-update-shop";
}


@RequestMapping(value = "/gotoUpdateShop")
public String gotoUpdateShop(ModelMap map,Integer shopId){
    Integer companyId = getCompanyId();
    ShopInfo shop = this.shopService.findShopByShopIdAndCompanyId(companyId, shopId);
    if (shop == null) {
        String ms = "店铺ID有误，请联系管理员 店铺ID：" + shopId;
        map.put("actionError", ms);
        logger.error(ms + " 此JS可能被篡改");
        return "error";
    }
    SaveOrUpdateShopDto dto = new SaveOrUpdateShopDto();
    BeanUtils.copyProperties(shop, dto);
    dto.setDiscount(shop.getDiscount().toString());
    dto.setTypeStatus(OPERATE_TYPE_UPDATE);
    map.put("shop", dto);
    return "shop/shop/saveOrUpdate/save-or-update-shop";
}


@RequestMapping(value = "/saveOrUpdateShop")
public String saveOrUpdateShop(SaveOrUpdateShopDto dto,BindingResult br,ModelMap map){
    map.put("shop", dto);
    if (br.hasErrors()) {
        return "shop/shop/saveOrUpdate/save-or-update-shop";
    }
    try {
        if (!(P_NAME_TAOBAO.equals(dto.getpName()) || P_NAME_YOUZAN.equals(dto.getpName()))) {
            throw new RuntimeException("平台名称有误：" + dto.getpName());
        }
        // 新增
        if (OPERATE_TYPE_ADD.equals(dto.getTypeStatus())) {
            dto.setCompanyId(getCompanyId());
            this.shopService.saveShop(dto);
            map.put("actionMessage", "[" + dto.getShopName() + "] 店铺 操作成功");
            return "forward:/shop/showCompanyInfo";
        // 修改
        } else if (OPERATE_TYPE_UPDATE.equals(dto.getTypeStatus())) {
            dto.setCompanyId(getCompanyId());
            this.shopService.updateShop(dto);
            map.put("actionMessage", "[" + dto.getShopName() + "] 店铺 操作成功");
            return "forward:/shop/showCompanyInfo";
        } else {
            throw new RuntimeException("不支持的操作类型: " + dto.getTypeStatus());
        }
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        logger.error(e.getMessage(), e);
        return "error";
    }
}


public Integer getCompanyId(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getCompanyId();
}


public String deleteShop(ModelMap map,Integer shopId){
    try {
        String shopName = this.shopService.deleteShop(getCompanyId(), shopId);
        map.put("actionMessage", "[" + shopName + "] 店铺 删除成功");
        return "forward:/shop/showCompanyInfo";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        logger.error(e.getMessage(), e);
        return "error";
    }
}


}