package net.shangtech.weixin.sys.controller;
 import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.site.entity.CustomPage;
import net.shangtech.weixin.site.entity.SiteInfo;
import net.shangtech.weixin.site.entity.SiteTemplate;
import net.shangtech.weixin.site.service.CustomPageService;
import net.shangtech.weixin.site.service.SiteInfoService;
import net.shangtech.weixin.site.service.SiteTemplateService;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.type.SiteTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/manage/application/site")
public class SiteController extends BaseController{

@Autowired
 private  SiteTemplateService templateService;

@Autowired
 private  SiteInfoService siteInfoService;

@Autowired
 private  CustomPageService pageService;


@RequestMapping("/template")
public String template(HttpServletRequest request){
    // 查询系统中可用的模模板供用户选择
    List<SiteTemplate> list = templateService.find();
    request.setAttribute("list", list);
    // 查询用户当前采用的模板以及网站相关的属性值,如果没有就默认为第一个
    return "user/application/site/template";
}


@RequestMapping("/info/list")
public String siteInfoList(HttpServletRequest request){
    List<SiteInfo> list = siteInfoService.find("where sysUserId=?", getUser(request).getId());
    request.setAttribute("list", list);
    return "user/application/site/info-list";
}


@RequestMapping("/page/delete")
public String pageDelete(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    pageService.delete(id);
    return success(response);
}


@RequestMapping("/info/save")
public String siteInfoSave(HttpServletRequest request,HttpServletResponse response,SiteInfo info){
    SysUser user = getUser(request);
    info.setSysUserId(user.getId());
    siteInfoService.save(info);
    return success(response);
}


@RequestMapping("/page/save")
public String pageSave(HttpServletRequest request,HttpServletResponse response,CustomPage page){
    SysUser user = getUser(request);
    page.setSysUserId(user.getId());
    page.setCreateTime(new Date());
    pageService.add(page);
    return success(response);
}


@RequestMapping("/page/form/temp")
public String pageFormTemp(HttpServletRequest request){
    Integer id = getId(request);
    SiteTemplate temp = templateService.find(id);
    request.setAttribute("temp", temp);
    return "user/application/site/page-form-temp";
}


@RequestMapping("/info/delete")
public String siteInfoDel(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    siteInfoService.delete(id);
    return success(response);
}


@RequestMapping("/page/form")
public String pageForm(HttpServletRequest request){
    List<SiteTemplate> list = templateService.find("where type=?", SiteTemplateType.CUSTOM_PAGE.getType());
    request.setAttribute("list", list);
    CustomPage page = new CustomPage();
    Integer id = getId(request);
    if (id != null) {
        page = pageService.find(id);
    }
    request.setAttribute("page", page);
    return "user/application/site/page-form";
}


@RequestMapping("/info/form")
public String siteInfoForm(HttpServletRequest request){
    SiteInfo info = new SiteInfo();
    Integer id = getId(request);
    if (id != null) {
        info = siteInfoService.find(id);
    }
    request.setAttribute("info", info);
    return "user/application/site/info-form";
}


@RequestMapping("/page/list")
public String pageList(HttpServletRequest request){
    SysUser user = getUser(request);
    List<CustomPage> list = pageService.find("where sysUserId=?", user.getId());
    request.setAttribute("list", list);
    return "user/application/site/page-list";
}


}