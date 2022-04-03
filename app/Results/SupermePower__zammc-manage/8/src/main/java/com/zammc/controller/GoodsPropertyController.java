package com.zammc.controller;
 import com.zammc.domain.goods.GoodsEntity;
import com.zammc.domain.goods.GoodsPropertyEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.goods.GoodsPropertyService;
import com.zammc.service.goods.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/goodsProperty")
@Slf4j
public class GoodsPropertyController {

@Autowired
 private  GoodsPropertyService goodsPropertyService;

@Autowired
 private  GoodsService goodsService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(GoodsPropertyEntity goodsPropertyEntity){
    ModelAndView modelAndView = new ModelAndView("goods/goods-property-add");
    try {
        List<GoodsEntity> goodsEntities = goodsService.queryAllNotSingleGoods();
        modelAndView.addObject("goodsList", goodsEntities);
    } catch (Exception e) {
        log.error("GoodsPropertyController toAdd Exception", e);
    }
    return modelAndView;
}


@RequestMapping(path = "/addGoodsProperty")
public Message addGoodsProperty(GoodsPropertyEntity goodsPropertyEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = goodsPropertyService.addGoodsProperty(goodsPropertyEntity, request);
    } catch (Exception e) {
        log.error("GoodsPropertyController addGoodsProperty goodsPropertyEntity -> {} \n Exception", goodsPropertyEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(path = "/disable")
public Message disable(GoodsPropertyEntity goodsPropertyEntity){
    Message message = null;
    try {
        message = goodsPropertyService.disable(goodsPropertyEntity);
    } catch (Exception e) {
        log.error("GoodsPropertyController disable id -> {} \n Exception", goodsPropertyEntity.getId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(path = "/enable")
public Message enable(GoodsPropertyEntity goodsPropertyEntity){
    Message message = null;
    try {
        message = goodsPropertyService.enable(goodsPropertyEntity);
    } catch (Exception e) {
        log.error("GoodsPropertyController enable id -> {} \n Exception", goodsPropertyEntity.getId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(path = "/delete")
public Message delete(GoodsPropertyEntity goodsPropertyEntity){
    Message message = null;
    try {
        message = goodsPropertyService.delete(goodsPropertyEntity);
    } catch (Exception e) {
        log.error("GoodsPropertyController delete id -> {} \n Exception", goodsPropertyEntity.getId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/queryGoodsPropertyPage")
public ModelAndView queryGoodsPropertyPage(PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("goods/goods-property-list");
    try {
        goodsPropertyService.queryGoodsPropertyPage(pageBean);
        List<GoodsEntity> goodsEntities = goodsService.queryAllNotSingleGoods();
        modelAndView.addObject("page", pageBean).addObject("goodsList", goodsEntities);
    } catch (Exception e) {
        log.error("GoodsPropertyController queryGoodsPropertyPage Exception", e);
    }
    return modelAndView;
}


}