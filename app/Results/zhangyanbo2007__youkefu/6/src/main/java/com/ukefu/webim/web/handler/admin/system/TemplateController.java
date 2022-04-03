package com.ukefu.webim.web.handler.admin.system;
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.service.repository.TemplateRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.Template;
import com.ukefu.webim.web.model.UKeFuDic;
@Controller
@RequestMapping("/admin/template")
public class TemplateController extends Handler{

@Autowired
 private  TemplateRepository templateRes;

@Autowired
 private  SysDicRepository dicRes;


@RequestMapping("/add")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView add(ModelMap map,HttpServletRequest request,String type){
    map.addAttribute("sysDic", dicRes.findById(type));
    return request(super.createRequestPageTempletResponse("/admin/system/template/add"));
}


@RequestMapping("/code")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView code(ModelMap map,HttpServletRequest request,String id,String type){
    map.addAttribute("sysDic", dicRes.findById(type));
    map.addAttribute("template", templateRes.findByIdAndOrgi(id, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/admin/system/template/code"));
}


@RequestMapping("/edit")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView edit(ModelMap map,HttpServletRequest request,String id,String type){
    map.addAttribute("sysDic", dicRes.findById(type));
    map.addAttribute("template", templateRes.findByIdAndOrgi(id, super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/admin/system/template/edit"));
}


@SuppressWarnings("unchecked")
@RequestMapping("/impsave")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView impsave(ModelMap map,HttpServletRequest request,MultipartFile dataFile){
    if (dataFile != null && dataFile.getSize() > 0) {
        List<Template> templateList = (List<Template>) UKTools.toObject(dataFile.getBytes());
        if (templateList != null && templateList.size() > 0) {
            templateRes.deleteInBatch(templateList);
            for (Template template : templateList) {
                templateRes.save(template);
            }
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/template/index.html"));
}


@RequestMapping("/save")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView save(HttpServletRequest request,Template template){
    template.setOrgi(super.getOrgi(request));
    template.setCreatetime(new Date());
    SysDic dic = dicRes.findById(template.getTemplettype());
    if (dic != null && StringUtils.isBlank(template.getCode())) {
        template.setCode(dic.getCode());
    }
    templateRes.save(template);
    return request(super.createRequestPageTempletResponse("redirect:/admin/template/list.html?type=" + template.getTemplettype()));
}


@RequestMapping("/index")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView index(ModelMap map,HttpServletRequest request){
    map.addAttribute("sysDicList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_DIC));
    return request(super.createAdminTempletResponse("/admin/system/template/index"));
}


@RequestMapping("/update")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView update(HttpServletRequest request,Template template){
    Template oldTemplate = templateRes.findByIdAndOrgi(template.getId(), super.getOrgi(request));
    if (oldTemplate != null) {
        SysDic dic = dicRes.findById(oldTemplate.getTemplettype());
        if (dic != null) {
            oldTemplate.setCode(dic.getCode());
        }
        if (!StringUtils.isBlank(template.getCode())) {
            oldTemplate.setCode(template.getCode());
        }
        oldTemplate.setName(template.getName());
        oldTemplate.setLayoutcols(template.getLayoutcols());
        oldTemplate.setIconstr(template.getIconstr());
        oldTemplate.setDatatype(template.getDatatype());
        oldTemplate.setCharttype(template.getCharttype());
        templateRes.save(oldTemplate);
        CacheHelper.getSystemCacheBean().delete(template.getId(), super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/template/list.html?type=" + template.getTemplettype()));
}


@RequestMapping("/codesave")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView codesave(HttpServletRequest request,Template template){
    Template oldTemplate = templateRes.findByIdAndOrgi(template.getId(), super.getOrgi(request));
    if (oldTemplate != null) {
        oldTemplate.setTemplettext(template.getTemplettext());
        oldTemplate.setTemplettitle(template.getTemplettitle());
        templateRes.save(oldTemplate);
        CacheHelper.getSystemCacheBean().delete(template.getId(), super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/template/list.html?type=" + template.getTemplettype()));
}


@RequestMapping("/expall")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public void expall(ModelMap map,HttpServletRequest request,HttpServletResponse response){
    List<Template> templateList = templateRes.findByOrgi(super.getOrgi(request));
    response.setHeader("content-disposition", "attachment;filename=UCKeFu-Template-Export-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".data");
    response.getOutputStream().write(UKTools.toBytes(templateList));
    return;
}


@RequestMapping("/list")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView list(ModelMap map,HttpServletRequest request,String type){
    map.addAttribute("sysDic", dicRes.findById(type));
    map.addAttribute("templateList", templateRes.findByTemplettypeAndOrgi(type, super.getOrgi(request)));
    return request(super.createAdminTempletResponse("/admin/system/template/list"));
}


@RequestMapping("/imp")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView imp(ModelMap map,HttpServletRequest request){
    return request(super.createRequestPageTempletResponse("/admin/system/template/imp"));
}


@RequestMapping("/delete")
@Menu(type = "admin", subtype = "template", access = false, admin = true)
public ModelAndView delete(HttpServletRequest request,Template template){
    if (template != null) {
        templateRes.delete(template);
        CacheHelper.getSystemCacheBean().delete(template.getId(), super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/template/list.html?type=" + template.getTemplettype()));
}


}