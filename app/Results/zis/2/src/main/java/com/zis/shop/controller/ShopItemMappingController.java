package com.zis.shop.controller;
 import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zis.common.mvc.ext.Token;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.bean.ShopItemMapping.ShopItemMappingSystemStatus;
import com.zis.shop.service.ShopService;
import com.zis.shop.util.ShopUtil;
@Controller
@RequestMapping(value = "/shop")
public class ShopItemMappingController {

 private  Logger logger;

 private  String MAPPING_WAIT;

@Autowired
 private  ShopService shopService;


@Token(checking = true)
@RequestMapping(value = "/addItemAll2Shop")
public String addItemAll2Shop(Integer shopId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        Integer i = this.shopService.addItem2Shop(shop);
        setSuccessMsg(map, "正在尝试" + i + "条数据发布，具体根据实际成功查询");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


@Token(generate = true)
@RequestMapping(value = "/queryShopItemMapping")
public String queryShopItemMapping(HttpServletRequest request,Integer shopId,String status,String isbn,ModelMap map){
    ShopInfo shop = null;
    try {
        shop = this.shopService.verifyShopId(shopId);
        setPageParam(map, shop, status);
        pageInfo(request, map, shopId, status, isbn);
        return getSuccessPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


public String getSuccessForWardPage(){
    return "forward:/shop/queryShopItemMapping";
}


public String getSuccessPage(){
    return "shop/shop/shopItemMapping/shop-item-mapping";
}


public void setPageParam(ModelMap map,ShopInfo shop,String mappingStatus){
    List<String> statusList = this.shopService.getStatusList();
    if (!statusList.contains(mappingStatus)) {
        mappingStatus = MAPPING_WAIT;
    }
    List<ShopInfo> shopList = this.shopService.findCompanyShop(ShopUtil.getCompanyId());
    map.put("shopId", shop.getShopId());
    map.put("shopList", shopList);
    map.put("mappingStatus", mappingStatus);
    map.put("shopPName", shop.getpName());
}


public void pageInfo(HttpServletRequest request,ModelMap map,Integer shopId,String status,String isbn){
    Pageable page = WebHelper.buildPageRequest(request);
    Page<ShopItemMapping> mappingList = this.shopService.queryShopItemMapping(shopId, status, isbn, page);
    if (!mappingList.getContent().isEmpty()) {
        List<ShopItemMapping> list = mappingList.getContent();
        map.put("mappingList", list);
        map.put("page", page.getPageNumber() + 1);
        setQueryConditionToPage(shopId, status, map);
        if (mappingList.hasPrevious()) {
            map.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (mappingList.hasNext()) {
            map.put("nextPage", page.next().getPageNumber());
        }
    } else {
        map.put("notResult", "未找到需要的结果");
    }
}


public void setSuccessMsg(ModelMap map,String msg){
    map.put("actionMessage", msg);
}


public String getErrorPage(){
    return "error";
}


@Token(checking = true)
@RequestMapping(value = "/failAddItemAll2Shop")
public String failAddItemAll2Shop(Integer shopId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        Integer i = this.shopService.failAddItem2Shop(shop);
        setSuccessMsg(map, "正在尝试" + i + "条数据发布，具体根据实际成功查询");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


@Token(checking = true)
@RequestMapping(value = "/failAddOneItem2Shop")
public String failAddOneItem2Shop(Integer shopId,Integer mId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        Integer i = this.shopService.failAddItem2Shop(mId, shop);
        setSuccessMsg(map, "发布成功 共发布了" + i + "条数据");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


public void setErrorMsg(ModelMap map,String msg){
    map.put("actionError", msg);
}


@Token(checking = true)
@RequestMapping(value = "/failAddItems2Shop")
public String failAddItems2Shop(Integer shopId,Integer[] mId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        List<Integer> list = Arrays.asList(mId);
        Integer i = this.shopService.failAddItem2Shop(list, shop);
        setSuccessMsg(map, "发布成功 共发布了" + i + "条数据");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


public void setQueryConditionToPage(Integer shopId,String shopStatus,ModelMap map){
    StringBuilder condition = new StringBuilder();
    if (shopId != null) {
        condition.append("shopId=" + shopId + "&");
    }
    if (StringUtils.isNotBlank(shopStatus)) {
        condition.append("status=" + shopStatus + "&");
    }
    map.put("queryCondition", condition.toString());
}


@Token(checking = true)
@RequestMapping(value = "/taobaoDownloadItems2Mapping")
public String taobaoDownloadItems2Mapping(MultipartFile excelFile,Integer shopId,ModelMap map){
    ShopInfo shop = null;
    try {
        shop = this.shopService.verifyShopId(shopId);
        if (!"taobao".equals(shop.getpName())) {
            setErrorMsg(map, "店铺非淘宝店铺");
            return getErrorPage();
        }
        List<ShopItemMapping> list = this.shopService.taobaoExeclToMapping(excelFile.getInputStream(), shop);
        this.shopService.asynchronousPrcessDownLoadMappingDataAndSendEmail(list, shop);
        setSuccessMsg(map, "数据正在努力下载中。。。您可以先处理其他店铺.");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


@Token(generate = true)
@RequestMapping(value = "/gotoTaobaoDownLoadJsp")
public String gotoTaobaoDownLoadJsp(Integer shopId,ModelMap map){
    map.put("shopId", shopId);
    return "shop/shop/shopItemMapping/taobao-download-xls";
}


@Token(checking = true)
@RequestMapping(value = "/addOneItem2Shop")
public String addOneItem2Shop(Integer shopId,Integer mId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        Integer i = this.shopService.addItem2Shop(mId, shop);
        setSuccessMsg(map, "发布成功 共发布了" + i + "条数据");
        setPageParam(map, shop, mappingStatus);
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


@Token(checking = true)
@RequestMapping(value = "/addItems2Shop")
public String addItems2Shop(Integer shopId,Integer[] mId,ModelMap map,String mappingStatus){
    try {
        ShopInfo shop = this.shopService.verifyShopId(shopId);
        List<Integer> list = Arrays.asList(mId);
        Integer i = this.shopService.addItem2Shop(list, shop);
        setSuccessMsg(map, "发布成功 共发布了" + i + "条数据");
        return getSuccessForWardPage();
    } catch (Exception e) {
        setErrorMsg(map, e.getMessage());
        logger.error(e.getMessage(), e);
        return getErrorPage();
    }
}


}