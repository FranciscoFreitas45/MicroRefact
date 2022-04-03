package net.shangtech.weixin.sys.controller;
 import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.util.FileUtils;
import net.shangtech.weixin.property.entity.HouseInfo;
import net.shangtech.weixin.property.entity.HousePanorama;
import net.shangtech.weixin.property.entity.Panorama;
import net.shangtech.weixin.property.entity.ProjectImage;
import net.shangtech.weixin.property.entity.ProjectType;
import net.shangtech.weixin.property.entity.SubProject;
import net.shangtech.weixin.property.service.ProjectService;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/manage/application/property")
public class PropertyController extends BaseController{

@Autowired
 private  ProjectService projectService;

 private  String PATH;


@RequestMapping("/projects")
public String projects(HttpServletRequest request){
    SysUser user = getUser(request);
    List<ProjectType> typeList = projectService.findProjectTypesByUser(user.getId());
    request.setAttribute("typeList", typeList);
    Map<Integer, List<SubProject>> map = new HashMap<Integer, List<SubProject>>();
    for (ProjectType type : typeList) {
        List<SubProject> list = projectService.findByProjectType(type.getId());
        map.put(type.getId(), list);
    }
    request.setAttribute("map", map);
    return PATH + "/frame";
}


@RequestMapping("/type/delete")
public String propertyTypeDelete(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    projectService.deleteProjectType(id);
    return success(response);
}


@RequestMapping("/project/save")
public String saveProject(HttpServletRequest request,HttpServletResponse response,SubProject project){
    SysUser user = getUser(request);
    project.setSysUserId(user.getId());
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 封面图
        MultipartFile image = multipartRequest.getFile("image_file");
        // 详情页封面图
        MultipartFile imageDescription = multipartRequest.getFile("image_description");
        // 周边配套页封面图
        MultipartFile imagePeripheral = multipartRequest.getFile("image_peripheral");
        // 交通配套封面图
        MultipartFile imageTraffic = multipartRequest.getFile("image_traffic");
        // 楼盘图片
        List<MultipartFile> projectImageList = multipartRequest.getFiles("project_image");
        if (image == null) {
            return failed(response, "请上传封面图");
        }
        project.setImage(FileUtils.saveStreamToFile(image.getInputStream(), image.getOriginalFilename()));
        project.setImageDescription(FileUtils.saveStreamToFile(imageDescription.getInputStream(), imageDescription.getOriginalFilename()));
        project.setImagePeripheral(FileUtils.saveStreamToFile(imagePeripheral.getInputStream(), imagePeripheral.getOriginalFilename()));
        project.setImageTraffic(FileUtils.saveStreamToFile(imageTraffic.getInputStream(), imageTraffic.getOriginalFilename()));
        List<ProjectImage> list = new LinkedList<ProjectImage>();
        for (MultipartFile file : projectImageList) {
            ProjectImage projectImage = new ProjectImage();
            projectImage.setImage(FileUtils.saveStreamToFile(file.getInputStream(), file.getOriginalFilename()));
            list.add(projectImage);
        }
        projectService.saveProject(project, list);
        JSONObject obj = new JSONObject();
        obj.put("success", true);
        obj.put("image", project.getImage());
        out(response, obj.toJSONString());
    } catch (ClassCastException e) {
        e.printStackTrace();
        return failed(response, "文件上传错误");
    } catch (IOException e) {
        e.printStackTrace();
        return failed(response, "文件保存失败");
    }
    return null;
}


@RequestMapping("/project/form")
public String projectForm(HttpServletRequest request){
    Integer id = getId(request);
    SubProject project = new SubProject();
    if (id != null) {
        project = projectService.find(id);
        List<HouseInfo> houses = projectService.findHousesByProject(id);
        request.setAttribute("houses", houses);
    } else {
        project.setType(getInt(request, "type"));
    }
    request.setAttribute("project", project);
    return PATH + "/project-form";
}


@RequestMapping("/panorama/save")
public String panoramaSave(HttpServletRequest request,HttpServletResponse response){
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile front = multipartRequest.getFile("image_front");
        MultipartFile back = multipartRequest.getFile("image_back");
        MultipartFile left = multipartRequest.getFile("image_left");
        MultipartFile right = multipartRequest.getFile("image_right");
        MultipartFile top = multipartRequest.getFile("image_top");
        MultipartFile bottom = multipartRequest.getFile("image_bottom");
        Panorama p = new Panorama();
        p.setImageFront(FileUtils.saveStreamToFile(front.getInputStream(), front.getOriginalFilename()));
        p.setImageBack(FileUtils.saveStreamToFile(back.getInputStream(), back.getOriginalFilename()));
        p.setImageLeft(FileUtils.saveStreamToFile(left.getInputStream(), left.getOriginalFilename()));
        p.setImageRight(FileUtils.saveStreamToFile(right.getInputStream(), right.getOriginalFilename()));
        p.setImageTop(FileUtils.saveStreamToFile(top.getInputStream(), top.getOriginalFilename()));
        p.setImageBottom(FileUtils.saveStreamToFile(bottom.getInputStream(), bottom.getOriginalFilename()));
        String title = request.getParameter("imageName");
        p.setTitle(title);
        HousePanorama hp = new HousePanorama();
        hp.setImageName(title);
        hp.setHouseId(getInt(request, "houseId"));
        hp.setSort(getInt(request, "sort"));
        hp.setPanorama(p);
        projectService.savePanorama(hp);
    } catch (IOException e) {
        e.printStackTrace();
        return failed(response, "文件保存出错");
    }
    return success(response);
}


@RequestMapping("/panorama/list")
public String panoramaList(HttpServletRequest request){
    Integer houseId = getInt(request, "house");
    List<HousePanorama> list = projectService.findPanoramasByHouse(houseId);
    request.setAttribute("list", list);
    return PATH + "/house-3d-images";
}


public String projectImages(){
    return null;
}


@RequestMapping("/panorama/delete")
public String panoramaDelete(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    projectService.deletePanorama(id);
    return success(response);
}


@RequestMapping("/project/delete")
public String projectDelete(HttpServletRequest request,HttpServletResponse response){
    Integer id = getId(request);
    projectService.delete(id);
    return success(response);
}


@RequestMapping("/house/save")
public String saveProjectHouse(HttpServletRequest request,HttpServletResponse response,HouseInfo house){
    try {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile image = multipartRequest.getFile("house_image");
        if (image != null) {
            house.setImage(FileUtils.saveStreamToFile(image.getInputStream(), image.getOriginalFilename()));
        }
        projectService.saveHouse(house);
    } catch (IOException e) {
        e.printStackTrace();
        return failed(response, "文件保存错误");
    }
    return success(response);
}


@RequestMapping("/projects/type")
public String projectsByType(HttpServletRequest request){
    Integer type = getInt(request, "type");
    List<SubProject> list = projectService.findByProjectType(type);
    request.setAttribute("list", list);
    return PATH + "/projects-by-type";
}


@RequestMapping("/project/houses")
public String projectHouses(HttpServletRequest request){
    Integer projectId = getInt(request, "projectId");
    List<HouseInfo> list = projectService.findHousesByProject(projectId);
    request.setAttribute("list", list);
    return PATH + "/project-houses";
}


@RequestMapping("/type/save")
public String projectTypeSave(HttpServletRequest request,HttpServletResponse response){
    SysUser user = getUser(request);
    Integer id = super.getId(request);
    String typeName = request.getParameter("typeName");
    String nameEn = request.getParameter("nameEn");
    ProjectType type = new ProjectType();
    type.setId(id);
    type.setName(typeName);
    type.setNameEn(nameEn);
    if (id == null) {
        type.setSort(projectService.countTypeByUser(user.getId()) + 1);
        type.setSysUserId(user.getId());
    }
    projectService.saveType(type);
    JSONObject obj = new JSONObject();
    obj.put("success", true);
    obj.put("msg", "保存成功");
    obj.put("id", type.getId());
    outJson(response, obj.toJSONString());
    return null;
}


}