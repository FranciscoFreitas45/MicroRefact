package net.shangtech.weixin.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.property.entity.HouseInfo;
import net.shangtech.weixin.property.entity.HousePanorama;
import net.shangtech.weixin.property.entity.Panorama;
import net.shangtech.weixin.property.entity.ProjectImage;
import net.shangtech.weixin.property.entity.ProjectType;
import net.shangtech.weixin.property.entity.SubProject;
import net.shangtech.weixin.property.service.HouseInfoService;
import net.shangtech.weixin.property.service.ProjectService;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller(value = "weixinPropertyController")
@RequestMapping("/weixin/application/property")
public class PropertyController extends BaseController{

@Autowired
 private  ProjectService projectService;

@Autowired
 private  HouseInfoService houseService;


@RequestMapping("/project/3d/house")
public String project3DHouse(HttpServletRequest request){
    Integer house = getInt(request, "house");
    HouseInfo houseInfo = houseService.find(house);
    SubProject project = projectService.find(houseInfo.getProjectId());
    request.setAttribute("project", project);
    List<HousePanorama> list = projectService.findPanoramasByHouse(house);
    request.setAttribute("list", list);
    return "weixin/property/project-house-3d";
}


@RequestMapping("/project/3d/panorama")
public String project3D(HttpServletRequest request){
    Integer id = getId(request);
    Panorama p = projectService.findPanoramaById(id);
    request.setAttribute("p", p);
    return "weixin/property/project-house-3d-panorama";
}


@RequestMapping("/project/peripheral")
public String projectPeripheral(HttpServletRequest request){
    Integer id = getId(request);
    SubProject project = projectService.find(id);
    request.setAttribute("project", project);
    return "weixin/property/project-peripheral";
}


@RequestMapping("/project/description")
public String projectDescription(HttpServletRequest request){
    Integer id = getId(request);
    SubProject project = projectService.find(id);
    request.setAttribute("project", project);
    return "weixin/property/project-description";
}


@RequestMapping("/project")
public String project(HttpServletRequest request){
    Integer id = getId(request);
    SubProject project = projectService.find(id);
    request.setAttribute("project", project);
    List<ProjectImage> images = projectService.findImagesByProject(project.getId());
    request.setAttribute("images", images);
    return "weixin/property/project";
}


@RequestMapping("/project/traffic")
public String projectTraffic(HttpServletRequest request){
    Integer id = getId(request);
    SubProject project = projectService.find(id);
    request.setAttribute("project", project);
    return "weixin/property/project-traffic";
}


@RequestMapping("/project/3d/panorama/data")
public String project3DData(HttpServletRequest request){
    Integer id = getId(request);
    Panorama p = projectService.findPanoramaById(id);
    request.setAttribute("p", p);
    return "weixin/property/project-house-3d-panorama-data";
}


@RequestMapping("/project/3d/houses")
public String project3DHouses(HttpServletRequest request){
    Integer id = getId(request);
    List<HouseInfo> list = projectService.findHousesByProject(id);
    request.setAttribute("list", list);
    return "weixin/property/project-houses-3d";
}


@RequestMapping("/projects")
public String projectsByType(HttpServletRequest request){
    SysUser user = getUser(request);
    List<ProjectType> typeList = projectService.findProjectTypesByUser(user.getId());
    request.setAttribute("typeList", typeList);
    Map<Integer, List<SubProject>> map = new HashMap<Integer, List<SubProject>>();
    for (ProjectType type : typeList) {
        List<SubProject> list = projectService.findByProjectType(type.getId());
        map.put(type.getId(), list);
    }
    request.setAttribute("map", map);
    return "weixin/property/projects";
}


@RequestMapping("/project/houses")
public String projectHouses(HttpServletRequest request){
    Integer id = getId(request);
    List<HouseInfo> list = projectService.findHousesByProject(id);
    request.setAttribute("list", list);
    return "weixin/property/project-houses";
}


}