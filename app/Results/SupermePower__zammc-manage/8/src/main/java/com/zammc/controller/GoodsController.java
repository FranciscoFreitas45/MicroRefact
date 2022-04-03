package com.zammc.controller;
 import com.zammc.domain.goods.GoodsCateEntity;
import com.zammc.domain.goods.GoodsEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.goods.GoodsCateService;
import com.zammc.service.goods.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.zammc.Interface.GoodsCateService;
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

@Autowired
 private  GoodsService goodsService;

@Autowired
 private  GoodsCateService goodsCateService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    ModelAndView modelAndView = new ModelAndView("goods/goods-add");
    try {
        List<GoodsCateEntity> goodsCateEntities = goodsCateService.queryCateList();
        modelAndView.addObject("cateList", goodsCateEntities);
    } catch (Exception e) {
        log.error("GoodsController toAdd Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/queryGoodsPage")
public ModelAndView queryGoodsPage(GoodsEntity goodsEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("goods/goods-list");
    try {
        goodsService.queryGoodsPage(goodsEntity, pageBean);
        List<GoodsCateEntity> goodsCateEntities = goodsCateService.queryCateList();
        modelAndView.addObject("page", pageBean).addObject("goods", goodsEntity).addObject("goodsCates", goodsCateEntities);
    } catch (Exception e) {
        log.error("GoodsController queryRechargeOrderPage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/shelfGoods")
@ResponseBody
public Message shelfGoods(GoodsEntity goodsEntity){
    Message message = null;
    try {
        goodsService.shelfGoods(goodsEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("GoodsController shelfGoods goodsId -> {} Exception \n", goodsEntity.getGoodsId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/dismountGoods")
@ResponseBody
public Message dismountGoods(GoodsEntity goodsEntity){
    Message message = null;
    try {
        goodsService.dismountGoods(goodsEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("GoodsController dismountGoods goodsId -> {} Exception \n", goodsEntity.getGoodsId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/deleteGoods")
@ResponseBody
public Message deleteGoods(GoodsEntity goodsEntity){
    Message message = null;
    try {
        goodsService.deleteGoods(goodsEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("GoodsController deleteGoods goodsId -> {} Exception \n", goodsEntity.getGoodsId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/addGoods")
@ResponseBody
public Message addGoods(GoodsEntity goodsEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = goodsService.addGoods(goodsEntity, request);
    } catch (Exception e) {
        log.error("GoodsController addGoods goodsEntity -> {} Exception", goodsEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "添加失败");
    }
    return message;
}


}