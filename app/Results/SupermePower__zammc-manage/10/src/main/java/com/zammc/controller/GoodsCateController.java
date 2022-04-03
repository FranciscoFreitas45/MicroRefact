package com.zammc.controller;
 import com.zammc.domain.goods.GoodsCateEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.goods.GoodsCateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/goodsCate")
@Slf4j
public class GoodsCateController {

@Autowired
 private  GoodsCateService goodsCateService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    return new ModelAndView("goods/cate-add");
}


@RequestMapping(value = "/forbiddenCate")
@ResponseBody
public Message forbiddenCate(GoodsCateEntity goodsCateEntity){
    Message message = null;
    try {
        goodsCateService.forbiddenCate(goodsCateEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("GoodsCateController forbiddenCate cateId -> {} Exception", goodsCateEntity.getCateId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/queryGoodsCatePage")
public ModelAndView queryGoodsCatePage(GoodsCateEntity goodsCateEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("goods/cate-list");
    try {
        goodsCateService.queryGoodsCatePage(goodsCateEntity, pageBean);
        modelAndView.addObject("page", pageBean).addObject("cate", goodsCateEntity);
    } catch (Exception e) {
        log.error("GoodsCateController queryGoodsCatePage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(GoodsCateEntity goodsCateEntity){
    ModelAndView modelAndView = new ModelAndView("goods/cate-edit");
    try {
        GoodsCateEntity goodsCate = goodsCateService.queryGoodsCateById(goodsCateEntity);
        modelAndView.addObject("goodsCate", goodsCate);
    } catch (Exception e) {
        log.error("GoodsCateController toEdit cateId -> {} Exception", goodsCateEntity.getCateId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/addGoodsCate")
@ResponseBody
public Message addGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = goodsCateService.addGoodsCate(goodsCateEntity, request);
    } catch (Exception e) {
        log.error("GoodsCateController addGoodsCate goodsCate -> {} Exception", goodsCateEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(value = "/startUsingCate")
@ResponseBody
public Message startUsingCate(GoodsCateEntity goodsCateEntity){
    Message message = null;
    try {
        goodsCateService.startUsingCate(goodsCateEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("GoodsCateController startUsingCate cateId -> {} Exception", goodsCateEntity.getCateId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/deleteGoodsCate")
@ResponseBody
public Message deleteGoodsCate(GoodsCateEntity goodsCateEntity){
    Message message = null;
    try {
        goodsCateService.deleteGoodsCate(goodsCateEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        e.printStackTrace();
        log.error("GoodsCateController deleteGoodsCate cateId -> {} Exception", goodsCateEntity.getCateId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/editGoodsCate")
@ResponseBody
public Message editGoodsCate(GoodsCateEntity goodsCateEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = goodsCateService.editGoodsCate(goodsCateEntity, request);
    } catch (Exception e) {
        log.error("GoodsCateController editGoodsCate goodsCate -> {} Exception \n", goodsCateEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "修改失败");
    }
    return message;
}


}