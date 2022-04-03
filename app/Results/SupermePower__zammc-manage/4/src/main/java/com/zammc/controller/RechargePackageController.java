package com.zammc.controller;
 import com.zammc.domain.recharge.RechargePackageEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.recharge.RechargePackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/rechargePackage")
@Slf4j
public class RechargePackageController {

@Autowired
 private  RechargePackageService rechargePackageService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    return new ModelAndView("recharge/recharge-package-add");
}


@RequestMapping("/queryRechargePackagePage")
public ModelAndView queryRechargePackagePage(RechargePackageEntity rechargePackageEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("recharge/recharge-package-list");
    try {
        rechargePackageService.queryRechargePackagePage(rechargePackageEntity, pageBean);
        modelAndView.addObject("rechargePackage", rechargePackageEntity).addObject("page", pageBean);
    } catch (Exception e) {
        log.error("RechargePackageController queryRechargePackagePage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/editRechargePackage")
@ResponseBody
public Message editRechargePackage(RechargePackageEntity rechargePackageEntity){
    Message message = null;
    try {
        message = rechargePackageService.editRechargePackage(rechargePackageEntity);
    } catch (Exception e) {
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "编辑失败");
    }
    return message;
}


@RequestMapping(value = "/addRechargePackage")
@ResponseBody
public Message addRechargePackage(RechargePackageEntity rechargePackageEntity){
    Message message = null;
    try {
        message = rechargePackageService.addRechargePackage(rechargePackageEntity);
    } catch (Exception e) {
        log.error("RechargePackageController addRechargePackage rechargePackage -> {} Exception \n", rechargePackageEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "新增失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(RechargePackageEntity rechargePackageEntity){
    ModelAndView modelAndView = new ModelAndView("recharge/recharge-package-edit");
    try {
        RechargePackageEntity rechargePackage = rechargePackageService.queryRechargePackageById(rechargePackageEntity);
        modelAndView.addObject("rechargePackage", rechargePackage);
    } catch (Exception e) {
        log.error("RechargePackageController toEdit packageId -> {} Exception \n", rechargePackageEntity.getPackageId(), e);
    }
    return modelAndView;
}


@RequestMapping("/deletePackage")
@ResponseBody
public Message deletePackage(RechargePackageEntity rechargePackageEntity){
    Message message = null;
    try {
        rechargePackageService.deletePackage(rechargePackageEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.成功, "删除成功");
    } catch (Exception e) {
        log.error("RechargePackageController deletePackage packageId -> {}", rechargePackageEntity.getPackageId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


}