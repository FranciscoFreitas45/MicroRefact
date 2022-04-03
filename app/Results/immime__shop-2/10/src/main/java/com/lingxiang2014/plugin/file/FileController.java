package com.lingxiang2014.plugin.file;
 import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.controller.shop.BaseController;
import com.lingxiang2014.entity.PluginConfig;
import com.lingxiang2014.service.PluginConfigService;
@Controller("adminPluginFileController")
@RequestMapping("/admin/storage_plugin/file")
public class FileController extends BaseController{

@Resource(name = "filePlugin")
 private  FilePlugin filePlugin;

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer order,RedirectAttributes redirectAttributes){
    PluginConfig pluginConfig = filePlugin.getPluginConfig();
    pluginConfig.setIsEnabled(true);
    pluginConfig.setOrder(order);
    pluginConfigService.update(pluginConfig);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:/admin/storage_plugin/list.jhtml";
}


@RequestMapping(value = "/setting", method = RequestMethod.GET)
public String setting(ModelMap model){
    PluginConfig pluginConfig = filePlugin.getPluginConfig();
    model.addAttribute("pluginConfig", pluginConfig);
    return "/net/shopxx/plugin/file/setting";
}


}