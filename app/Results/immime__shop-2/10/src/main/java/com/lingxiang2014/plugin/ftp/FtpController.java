package com.lingxiang2014.plugin.ftp;
 import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.PluginConfig;
import com.lingxiang2014.service.PluginConfigService;
@Controller("adminPluginFtpController")
@RequestMapping("/admin/storage_plugin/ftp")
public class FtpController extends BaseController{

@Resource(name = "ftpPlugin")
 private  FtpPlugin ftpPlugin;

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
@ResponseBody
public Message uninstall(){
    if (ftpPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
        pluginConfigService.delete(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/install", method = RequestMethod.POST)
@ResponseBody
public Message install(){
    if (!ftpPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.setPluginId(ftpPlugin.getId());
        pluginConfig.setIsEnabled(false);
        pluginConfigService.save(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String host,Integer port,String username,String password,String urlPrefix,Boolean isEnabled,Integer order,RedirectAttributes redirectAttributes){
    PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
    pluginConfig.setAttribute("host", host);
    pluginConfig.setAttribute("port", port.toString());
    pluginConfig.setAttribute("username", username);
    pluginConfig.setAttribute("password", password);
    pluginConfig.setAttribute("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
    pluginConfig.setIsEnabled(isEnabled);
    pluginConfig.setOrder(order);
    pluginConfigService.update(pluginConfig);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:/admin/storage_plugin/list.jhtml";
}


@RequestMapping(value = "/setting", method = RequestMethod.GET)
public String setting(ModelMap model){
    PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
    model.addAttribute("pluginConfig", pluginConfig);
    return "/net/shopxx/plugin/ftp/setting";
}


}