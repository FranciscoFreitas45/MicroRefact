package com.easyshopping.plugin.oss;
 import java.math.BigDecimal;
import javax.annotation.Resource;
import com.easyshopping.Message;
import com.easyshopping.controller.admin.BaseController;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.service.PluginConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.DTO.Message;
@Controller("adminPluginOssController")
@RequestMapping("/admin/storage_plugin/oss")
public class OssController extends BaseController{

@Resource(name = "ossPlugin")
 private  OssPlugin ossPlugin;

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
@ResponseBody
public Message uninstall(){
    if (ossPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = ossPlugin.getPluginConfig();
        pluginConfigService.delete(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/install", method = RequestMethod.POST)
@ResponseBody
public Message install(){
    String specificationVersion = System.getProperty("java.specification.version");
    if (StringUtils.isNotEmpty(specificationVersion)) {
        BigDecimal version = new BigDecimal(specificationVersion);
        if (version.compareTo(new BigDecimal("1.6")) < 0) {
            return Message.error("admin.plugin.oss.unsupportedJavaVersion");
        }
    }
    if (!ossPlugin.getIsInstalled()) {
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.setPluginId(ossPlugin.getId());
        pluginConfig.setIsEnabled(false);
        pluginConfigService.save(pluginConfig);
    }
    return SUCCESS_MESSAGE;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(String accessId,String accessKey,String bucketName,String urlPrefix,Boolean isEnabled,Integer order,RedirectAttributes redirectAttributes){
    PluginConfig pluginConfig = ossPlugin.getPluginConfig();
    pluginConfig.setAttribute("accessId", accessId);
    pluginConfig.setAttribute("accessKey", accessKey);
    pluginConfig.setAttribute("bucketName", bucketName);
    pluginConfig.setAttribute("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
    pluginConfig.setIsEnabled(isEnabled);
    pluginConfig.setOrder(order);
    pluginConfigService.update(pluginConfig);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:/admin/storage_plugin/list.jhtml";
}


@RequestMapping(value = "/setting", method = RequestMethod.GET)
public String setting(ModelMap model){
    PluginConfig pluginConfig = ossPlugin.getPluginConfig();
    model.addAttribute("pluginConfig", pluginConfig);
    return "/com/easyshopping/plugin/oss/setting";
}


}