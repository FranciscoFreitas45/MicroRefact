package com.zammc.controller;
 import com.zammc.domain.banner.BannerEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.banner.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/banner")
@Slf4j
public class BannerController {

@Autowired
 private  BannerService bannerService;


@RequestMapping("/toAdd")
public ModelAndView toAdd(BannerEntity bannerEntity){
    return new ModelAndView("banner/banner-add");
}


@RequestMapping(value = "/addBanner")
@ResponseBody
public Message addBanner(BannerEntity bannerEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = bannerService.addBanner(bannerEntity, request);
    } catch (Exception e) {
        log.error("BannerController addBanner bannerEntity -> {} Exception \n", bannerEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(value = "/deleteBanner")
@ResponseBody
public Message deleteBanner(BannerEntity bannerEntity){
    Message message = null;
    try {
        bannerService.deleteBanner(bannerEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("BannerController deleteBanner Exception \n", e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(BannerEntity bannerEntity){
    ModelAndView modelAndView = new ModelAndView("banner/banner-edit");
    try {
        BannerEntity banner = bannerService.queryBannerById(bannerEntity);
        modelAndView.addObject("banner", banner);
    } catch (Exception e) {
        log.error("BannerController toEdit bannerId -> {} Exception \n", bannerEntity.getBannerId(), e);
    }
    return modelAndView;
}


@RequestMapping("/editBanner")
@ResponseBody
public Message editBanner(BannerEntity bannerEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = bannerService.editBanner(bannerEntity, request);
    } catch (Exception e) {
        log.error("BannerController editBanner bannerEntity -> {} Exception \n", bannerEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "修改失败");
    }
    return message;
}


@RequestMapping(value = "/queryBannerPage")
public ModelAndView queryBannerPage(BannerEntity bannerEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("banner/banner-list");
    try {
        bannerService.queryBannerPage(bannerEntity, pageBean);
        modelAndView.addObject("banner", bannerEntity).addObject("page", pageBean);
    } catch (Exception e) {
        log.error("BannerController queryBannerPage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/forbiddenBanner")
@ResponseBody
public Message forbiddenBanner(BannerEntity bannerEntity){
    Message message = null;
    try {
        bannerService.forbiddenBanner(bannerEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("BannerController forbiddenBanner bannerId -> {} Exception \n", bannerEntity.getBannerId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/startUsingBanner")
@ResponseBody
public Message startUsingBanner(BannerEntity bannerEntity){
    Message message = null;
    try {
        bannerService.startUsingBanner(bannerEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "操作成功");
    } catch (Exception e) {
        log.error("BannerController startUsingBanner bannerId -> {} Exception \n", bannerEntity.getBannerId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


}