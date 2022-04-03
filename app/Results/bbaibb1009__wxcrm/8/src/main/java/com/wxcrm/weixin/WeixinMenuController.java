package com.wxcrm.weixin;
 import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.pojo.LzWeiMenu;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWeixinEnterService;
import com.wxcrm.DTO.LzWeiEnter;
@Controller
@RequestMapping("/weixinmenu")
public class WeixinMenuController {

@Autowired
 private  IWeixinMenuService weixinMenuService;

@Autowired
 private  IWeixinService weixinservice;

@Autowired
 private  IWeixinEnterService weixinenterservice;

 private  Logger log;


@RequestMapping(value = "/toAddMenu")
public String toAddMenu(LzWeiMenu menu,Model model){
    return "/weixin/addMenu";
}


@RequestMapping("/toUpdWeiXinMenu/{wecId}")
public String toUpdWeiXinMenu(LzWeiEnter weiEnter,Integer wecId,Model model){
    weiEnter = weixinservice.getWeiEnterById(wecId);
    LzWeiMenu menu = weixinMenuService.getMenuByAppId(weiEnter.getWecAppId());
    if (menu != null) {
        menu.setWmuWecId(wecId);
        menu.setWmuAppSecret(weiEnter.getWecAppSecret());
        model.addAttribute("weiEnter", weiEnter);
        model.addAttribute("command", menu);
        return "/weixin/updMenuByWeiEnter";
    } else {
        menu = new LzWeiMenu();
        menu.setWmuAppId(weiEnter.getWecAppId());
        menu.setWmuWecId(wecId);
        model.addAttribute("weiEnter", weiEnter);
        model.addAttribute("command", menu);
        return "/weixin/addMenuByWeiEnter";
    }
}


@RequestMapping(value = "/addMenu")
public String addMenu(LzWeiMenu menu,RedirectAttributes redirectattributes){
    String appId_curent = menu.getWmuAppId();
    String appSecret_current = menu.getWmuAppSecret();
    LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId_curent);
    String jsonMenu = menu.getWmuJson();
    String accessTokenStr = weixinservice.getCurrentAccessTokenStr(enter.getWecId());
    if (null != accessTokenStr) {
        // ���ýӿڴ����˵�
        // int result = WeixinUtil.createMenu(getMenu(),at.getWatToken());
        int result = weixinMenuService.addMenu(menu, accessTokenStr);
        // �жϲ˵��������
        if (0 == result) {
            log.info("�˵������ɹ���");
            redirectattributes.addFlashAttribute("alertMsg", "�˵������ɹ���");
        } else {
            log.info("�˵�����ʧ�ܣ������룺" + result);
            redirectattributes.addFlashAttribute("alertMsg", "�˵�����ʧ�ܣ������룺" + result);
        }
    }
    return "redirect:/weixinmenu/queryMenu";
}


@RequestMapping(value = "/addMenuByEnter")
public String addMenuByEnter(LzWeiMenu menu,RedirectAttributes redirectattributes){
    String appId_curent = menu.getWmuAppId();
    String appSecret_current = menu.getWmuAppSecret();
    LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId_curent);
    String jsonMenu = menu.getWmuJson();
    String accessTokenStr = weixinservice.getCurrentAccessTokenStr(enter.getWecId());
    if (null != accessTokenStr) {
        // ���ýӿڴ����˵�
        // int result = WeixinUtil.createMenu(getMenu(),at.getWatToken());
        int result = weixinMenuService.addMenu(menu, accessTokenStr);
        // �жϲ˵��������
        if (0 == result) {
            log.info("�˵������ɹ���");
            redirectattributes.addFlashAttribute("alertMsg", "�˵������ɹ���");
        } else {
            log.info("�˵�����ʧ�ܣ������룺" + result);
            redirectattributes.addFlashAttribute("alertMsg", "�˵�����ʧ�ܣ������룺" + result);
        }
    }
    return "redirect:/weixin/queryWeixinEnter";
}


@RequestMapping(value = "/toUpdMenu", method = RequestMethod.POST)
public String toUpdAdmin(LzWeiMenu menu_Q,Model model){
    LzWeiMenu menu = weixinMenuService.getMenuById(menu_Q.getWmuId());
    StringUtil.copyProperties(menu_Q, menu);
    model.addAttribute("command", menu);
    return "/weixin/updMenu";
}


@RequestMapping(value = "/queryMenu")
public String queryMenu(LzWeiMenu menu,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, weixinMenuService.queryMenu(menu));
    return "/weixin/queryMenu";
}


}