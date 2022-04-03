package com.ukefu.webim.web.handler.admin.organ;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.AreaTypeRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.OrganRoleRepository;
import com.ukefu.webim.service.repository.RoleRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.OrganRole;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.User;
import Interface.AreaTypeRepository;
@Controller
@RequestMapping("/admin/organ")
public class OrganController extends Handler{

@Autowired
 private  OrganRepository organRepository;

@Autowired
 private  RoleRepository roleRepository;

@Autowired
 private  SysDicRepository sysDicRepository;

@Autowired
 private  AreaTypeRepository areaRepository;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  OrganRoleRepository organRoleRes;

@Autowired
 private  SysDicRepository sysDicRes;


@RequestMapping("/add")
@Menu(type = "admin", subtype = "organ")
public ModelAndView add(ModelMap map,HttpServletRequest request,String parent,String area){
    map.addAttribute("areaList", areaRepository.findByOrgi(super.getOrgiByTenantshare(request)));
    if (!StringUtils.isBlank(parent)) {
        map.addAttribute("organ", organRepository.findByIdAndOrgi(parent, super.getOrgiByTenantshare(request)));
    }
    if (!StringUtils.isBlank(area)) {
        map.addAttribute("area", areaRepository.findByIdAndOrgi(area, super.getOrgiByTenantshare(request)));
    }
    map.addAttribute("organList", organRepository.findByOrgiAndOrgid(super.getOrgiByTenantshare(request), super.getOrgid(request)));
    return request(super.createRequestPageTempletResponse("/admin/organ/add"));
}


@RequestMapping("/area")
@Menu(type = "admin", subtype = "area")
public ModelAndView area(ModelMap map,HttpServletRequest request,String id){
    SysDic sysDic = sysDicRepository.findByCode(UKDataContext.UKEFU_SYSTEM_AREA_DIC);
    if (sysDic != null) {
        map.addAttribute("sysarea", sysDic);
        map.addAttribute("areaList", sysDicRepository.findByDicid(sysDic.getId()));
    }
    map.addAttribute("cacheList", UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_AREA_DIC));
    map.addAttribute("organData", organRepository.findByIdAndOrgi(id, super.getOrgiByTenantshare(request)));
    return request(super.createRequestPageTempletResponse("/admin/organ/area"));
}


@RequestMapping("/edit")
@Menu(type = "admin", subtype = "organ")
public ModelAndView edit(ModelMap map,HttpServletRequest request,String id){
    ModelAndView view = request(super.createRequestPageTempletResponse("/admin/organ/edit"));
    map.addAttribute("areaList", areaRepository.findByOrgi(super.getOrgiByTenantshare(request)));
    view.addObject("organData", organRepository.findByIdAndOrgi(id, super.getOrgiByTenantshare(request)));
    map.addAttribute("organList", organRepository.findByOrgiAndOrgid(super.getOrgiByTenantshare(request), super.getOrgid(request)));
    return view;
}


@RequestMapping("/auth")
@Menu(type = "admin", subtype = "organ")
public ModelAndView auth(ModelMap map,HttpServletRequest request,String id){
    SysDic sysDic = sysDicRes.findByCode(UKDataContext.UKEFU_SYSTEM_AUTH_DIC);
    if (sysDic != null) {
        map.addAttribute("resourceList", sysDicRes.findByDicid(sysDic.getId(), new PageRequest(0, 1000, Direction.ASC, "createtime")).getContent());
    }
    map.addAttribute("sysDic", sysDic);
    Organ organData = organRepository.findByIdAndOrgi(id, super.getOrgiByTenantshare(request));
    map.addAttribute("organData", organData);
    map.addAttribute("roleList", roleRepository.findByOrgiAndOrgid(super.getOrgiByTenantshare(request), super.getOrgid(request)));
    map.addAttribute("organRoleList", organRoleRes.findByOrgiAndOrgan(super.getOrgiByTenantshare(request), organData));
    return request(super.createRequestPageTempletResponse("/admin/organ/auth"));
}


@RequestMapping("/save")
@Menu(type = "admin", subtype = "organ")
public ModelAndView save(HttpServletRequest request,Organ organ){
    Organ tempOrgan = organRepository.findByNameAndOrgiAndOrgid(organ.getName(), super.getOrgiByTenantshare(request), super.getOrgid(request));
    String msg = "admin_organ_save_success";
    String firstId = null;
    if (tempOrgan != null) {
        // 分类名字重复
        msg = "admin_organ_update_name_not";
    } else {
        organ.setOrgi(super.getOrgiByTenantshare(request));
        if (!StringUtils.isBlank(super.getUser(request).getOrgid())) {
            organ.setOrgid(super.getUser(request).getOrgid());
        } else {
            organ.setOrgid(UKDataContext.SYSTEM_ORGI);
        }
        firstId = organ.getId();
        organRepository.save(organ);
        OnlineUserUtils.clean(super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?msg=" + msg + "&organ=" + firstId));
}


@RequestMapping("/index")
@Menu(type = "admin", subtype = "organ")
public ModelAndView index(ModelMap map,HttpServletRequest request,String organ,String msg){
    List<Organ> organList = organRepository.findByOrgiAndOrgid(super.getOrgiByTenantshare(request), super.getOrgid(request));
    map.addAttribute("organList", organList);
    if (organList.size() > 0) {
        Organ organData = null;
        if (!StringUtils.isBlank(organ) && !"null".equals(organ)) {
            for (Organ data : organList) {
                if (data.getId().equals(organ)) {
                    map.addAttribute("organData", data);
                    organData = data;
                }
            }
        } else {
            map.addAttribute("organData", organData = organList.get(0));
        }
        if (organData != null) {
            map.addAttribute("userList", userRepository.findByDatastatusAndOrganAndOrgi(false, organData.getId(), super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" })));
        }
    }
    map.addAttribute("areaList", areaRepository.findByOrgi(super.getOrgiByTenantshare(request)));
    map.addAttribute("roleList", roleRepository.findByOrgiAndOrgid(super.getOrgiByTenantshare(request), super.getOrgid(request)));
    map.put("msg", msg);
    map.put("pagetemp", request.getParameter("pagetemp"));
    return request(super.createAdminTempletResponse("/admin/organ/index"));
}


@RequestMapping("/update")
@Menu(type = "admin", subtype = "organ")
public ModelAndView update(HttpServletRequest request,Organ organ){
    Organ org = organRepository.findByNameAndOrgi(organ.getName(), super.getOrgi(request));
    String msg = "admin_organ_update_success";
    if (org != null) {
        if (org.getId().equals(organ.getId())) {
            Organ tempOrgan = organRepository.findByIdAndOrgi(organ.getId(), super.getOrgiByTenantshare(request));
            if (tempOrgan != null) {
                tempOrgan.setName(organ.getName());
                tempOrgan.setUpdatetime(new Date());
                tempOrgan.setOrgi(super.getOrgiByTenantshare(request));
                tempOrgan.setSkill(organ.isSkill());
                tempOrgan.setParent(organ.getParent());
                tempOrgan.setArea(organ.getArea());
                if (!StringUtils.isBlank(super.getUser(request).getOrgid())) {
                    tempOrgan.setOrgid(super.getUser(request).getOrgid());
                } else {
                    tempOrgan.setOrgid(UKDataContext.SYSTEM_ORGI);
                }
                organRepository.save(tempOrgan);
                OnlineUserUtils.clean(super.getOrgi(request));
            } else {
                // 修改失败
                msg = "admin_organ_update_not_exist";
            }
        } else {
            // 分类名字重复
            msg = "admin_organ_update_name_not";
        }
    } else {
        Organ tempOrgan = organRepository.findByIdAndOrgi(organ.getId(), super.getOrgiByTenantshare(request));
        if (tempOrgan != null) {
            tempOrgan.setName(organ.getName());
            tempOrgan.setUpdatetime(new Date());
            tempOrgan.setOrgi(super.getOrgiByTenantshare(request));
            tempOrgan.setSkill(organ.isSkill());
            tempOrgan.setParent(organ.getParent());
            tempOrgan.setArea(organ.getArea());
            if (!StringUtils.isBlank(super.getUser(request).getOrgid())) {
                tempOrgan.setOrgid(super.getUser(request).getOrgid());
            } else {
                tempOrgan.setOrgid(UKDataContext.SYSTEM_ORGI);
            }
            organRepository.save(tempOrgan);
            OnlineUserUtils.clean(super.getOrgi(request));
        } else {
            // 修改失败
            msg = "admin_organ_update_not_exist";
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?msg=" + msg + "&organ=" + organ.getId()));
}


@RequestMapping("/auth/save")
@Menu(type = "admin", subtype = "role")
public ModelAndView authsave(HttpServletRequest request,String id,String menus){
    Organ organData = organRepository.findByIdAndOrgi(id, super.getOrgiByTenantshare(request));
    List<OrganRole> organRoleList = organRoleRes.findByOrgiAndOrgan(super.getOrgiByTenantshare(request), organData);
    organRoleRes.delete(organRoleList);
    if (!StringUtils.isBlank(menus)) {
        String[] menusarray = menus.split(",");
        for (String menu : menusarray) {
            OrganRole organRole = new OrganRole();
            SysDic sysDic = UKeFuDic.getInstance().getDicItem(menu);
            if (sysDic == null) {
                sysDic = sysDicRepository.findById(menu);
            }
            if (sysDic != null && !"0".equals(sysDic.getParentid())) {
                organRole.setDicid(menu);
                organRole.setDicvalue(sysDic.getCode());
                organRole.setOrgan(organData);
                organRole.setCreater(super.getUser(request).getId());
                organRole.setOrgi(super.getOrgiByTenantshare(request));
                organRole.setCreatetime(new Date());
                organRoleRes.save(organRole);
            }
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?organ=" + organData.getId()));
}


@RequestMapping("/seluser")
@Menu(type = "admin", subtype = "seluser", admin = true)
public ModelAndView seluser(ModelMap map,HttpServletRequest request,String organ){
    map.addAttribute("userList", userRepository.findByOrgiAndDatastatusAndOrgid(super.getOrgiByTenantshare(request), false, super.getOrgid(request)));
    Organ organData = organRepository.findByIdAndOrgi(organ, super.getOrgiByTenantshare(request));
    map.addAttribute("userOrganList", userRepository.findByOrganAndOrgiAndDatastatus(organ, super.getOrgiByTenantshare(request), false));
    map.addAttribute("organ", organData);
    return request(super.createRequestPageTempletResponse("/admin/organ/seluser"));
}


@RequestMapping("/saveuser")
@Menu(type = "admin", subtype = "saveuser", admin = true)
public ModelAndView saveuser(HttpServletRequest request,String[] users,String organ){
    List<String> userList = new ArrayList<String>();
    if (users != null && users.length > 0) {
        for (String user : users) {
            userList.add(user);
        }
        Organ organData = organRepository.findByIdAndOrgi(organ, super.getOrgiByTenantshare(request));
        List<User> organUserList = userRepository.findAll(userList);
        for (User user : organUserList) {
            user.setOrgan(organ);
            /**
             * 以下更新技能组状态
             */
            AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(user.getId(), super.getOrgiByTenantshare(request));
            if (agentStatus != null) {
                agentStatus.setSkill(organ);
                agentStatus.setSkillname(organData.getName());
                CacheHelper.getAgentStatusCacheBean().delete(user.getId(), user.getOrgi());
                CacheHelper.getAgentStatusCacheBean().put(user.getId(), agentStatus, super.getOrgiByTenantshare(request));
            }
        }
        userRepository.save(organUserList);
        OnlineUserUtils.clean(super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?organ=" + organ));
}


@RequestMapping("/delete")
@Menu(type = "admin", subtype = "organ")
public ModelAndView delete(HttpServletRequest request,Organ organ){
    String msg = "admin_organ_delete";
    if (organ != null) {
        organRepository.delete(organ);
        OnlineUserUtils.clean(super.getOrgi(request));
    } else {
        msg = "admin_organ_not_exist";
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?msg=" + msg));
}


@RequestMapping("/user/delete")
@Menu(type = "admin", subtype = "role")
public ModelAndView userroledelete(HttpServletRequest request,String id,String organ){
    if (id != null) {
        User user = userRepository.getOne(id);
        user.setOrgan(null);
        userRepository.save(user);
        OnlineUserUtils.clean(super.getOrgi(request));
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?organ=" + organ));
}


@RequestMapping("/area/update")
@Menu(type = "admin", subtype = "organ")
public ModelAndView areaupdate(HttpServletRequest request,Organ organ){
    Organ tempOrgan = organRepository.findByIdAndOrgi(organ.getId(), super.getOrgiByTenantshare(request));
    String msg = "admin_organ_update_success";
    if (tempOrgan != null) {
        tempOrgan.setArea(organ.getArea());
        organRepository.save(tempOrgan);
        OnlineUserUtils.clean(super.getOrgi(request));
    } else {
        msg = "admin_organ_update_not_exist";
    }
    return request(super.createRequestPageTempletResponse("redirect:/admin/organ/index.html?msg=" + msg + "&organ=" + organ.getId()));
}


}