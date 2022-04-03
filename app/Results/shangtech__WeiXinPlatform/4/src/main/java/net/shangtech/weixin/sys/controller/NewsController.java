package net.shangtech.weixin.sys.controller;
 import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.util.FileUtils;
import net.shangtech.weixin.site.entity.News;
import net.shangtech.weixin.site.entity.NewsType;
import net.shangtech.weixin.site.service.NewsService;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
@Controller
@RequestMapping("/manage/application/news")
public class NewsController extends BaseController{

@Autowired
 private  NewsService service;

 private  String PATH;


@RequestMapping("/type/save")
public String saveType(HttpServletRequest request,HttpServletResponse response,NewsType type){
    SysUser user = getUser(request);
    type.setSysUserId(user.getId());
    service.saveType(type);
    return success(response);
}


@RequestMapping("/save")
public String saveNews(HttpServletRequest request,HttpServletResponse response,News news){
    SysUser user = getUser(request);
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile image = multipartRequest.getFile("image_file");
        if (image != null) {
            news.setImage(FileUtils.saveStreamToFile(image.getInputStream(), image.getOriginalFilename()));
        }
        news.setSysUserId(user.getId());
        service.saveNews(news);
    } catch (IOException e) {
        return failed(response, "文件保存出错");
    }
    return success(response);
}


@RequestMapping("/type/list")
public String typeList(HttpServletRequest request){
    SysUser user = getUser(request);
    List<NewsType> list = service.findNewsTypeByUserId(user.getId());
    request.setAttribute("list", list);
    return PATH + "/type-list";
}


@RequestMapping("/type/form")
public String typeForm(HttpServletRequest request){
    Integer id = getId(request);
    NewsType type = new NewsType();
    if (id != null) {
        type = service.findType(id);
    }
    request.setAttribute("type", type);
    return PATH + "/type-form";
}


@RequestMapping("/type/delete")
public String deleteType(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    service.deleteType(id);
    return success(response);
}


@RequestMapping("/form")
public String newsForm(HttpServletRequest request){
    SysUser user = getUser(request);
    List<NewsType> typeList = service.findNewsTypeByUserId(user.getId());
    request.setAttribute("typeList", typeList);
    Integer id = getId(request);
    News news = new News();
    if (id != null) {
        news = service.find(id);
    }
    request.setAttribute("news", news);
    return PATH + "/form";
}


@RequestMapping("/list")
public String list(HttpServletRequest request){
    SysUser user = getUser(request);
    List<News> list = service.findAllNewsBySysUser(user.getId());
    for (News news : list) {
        news.setNewsType(service.findType(news.getType()));
    }
    request.setAttribute("list", list);
    return PATH + "/list";
}


@RequestMapping("/delete")
public String delete(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    service.delete(id);
    return success(response);
}


}