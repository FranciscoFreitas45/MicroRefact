package com.wxcrm.website;
 import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IShopAdminService;
import com.wxcrm.DTO.WcShopAdmin;
@Controller
@RequestMapping("/website")
public class WebsiteController {

@Autowired
 private  IWebsiteService websiteService;

@Autowired
 private  IAdminService adminservice;

@Autowired
 private  IShopAdminService adminshopservice;


@RequestMapping(value = "/toAddWebsite")
public String toAddWebsite(WcWebsite webSite,Model model){
    return "/website/addWebsite";
}


@RequestMapping(value = "/queryWebsite")
public String queryTest(WcWebsite webSite,Model model){
    model.addAttribute(SysConstant.PAGE_RESULT, websiteService.queryWebsite(webSite));
    return "/website/queryWebsite";
}


@RequestMapping(value = "/toUpdWebSite", method = RequestMethod.POST)
public String toUpdWebSite(WcWebsite website_Q,Model model){
    WcWebsite website = websiteService.getWebSiteById(website_Q.getWcsId());
    StringUtil.copyProperties(website_Q, website);
    model.addAttribute("command", website);
    if (website.getWcsAdminId() != null) {
        WcShopAdmin admin = adminshopservice.getShopAdminById(website.getWcsAdminId());
        model.addAttribute("adminName", admin.getWsaName());
    }
    return "/website/updWebsite";
}


@RequestMapping("/updWebsite")
public String updWebSite(WcWebsite webSite,HttpServletRequest request,RedirectAttributes redirectAttribute){
    websiteService.updWebSite(webSite);
    redirectAttribute.addFlashAttribute("msgCode", "2");
    redirectAttribute.addFlashAttribute("alertMsg", "վ���޸ĳɹ�");
    redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/website/queryWebsite", webSite));
    return "redirect:/admin/toMsg";
}


@RequestMapping(value = "/addWebsite")
public String addWebsite(WcWebsite webSite,RedirectAttributes redirectAttribute){
    websiteService.addWebsite(webSite);
    redirectAttribute.addFlashAttribute("alertMsg", "���վ��ɹ�!");
    return "redirect:/website/queryWebsite";
}


}