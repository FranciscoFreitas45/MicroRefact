package com.gbcom.system.controller;
 import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysCodeDetailService;
import com.gbcom.system.daoservice.SysCodeService;
import com.gbcom.system.domain.SysCode;
import com.gbcom.system.domain.SysCodeDetail;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.DTO.ZTreeBranch;
import com.gbcom.DTO.ZTreeNode;
@Controller
public class SysCodeController extends BaseCRUDActionController<SysCode>{

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysCodeService sysCodeService;

@Autowired
 private  SysCodeDetailService sysCodeDetailService;


@RequestMapping
@UserLog
public String add(Model model){
    SysCode sysCode = new SysCode();
    // 如需增加其他默认值请在此添加
    model.addAttribute("bean", sysCode);
    return "view/system/sysCode/input";
}


@RequestMapping
@UserLog
public String init(Model model){
    return "view/system/sysCode/init";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysCode sysCode = sysCodeService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysCode);
    return "view/system/sysCode/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysCode sysCode = sysCodeService.get(id);
    model.addAttribute("bean", sysCode);
    return "view/system/sysCode/view";
}


@RequestMapping
public String tree(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_CODE_EDIT));
    model.addAttribute("clazz", SysCode.class.getName());
    model.addAttribute("clazzDetail", SysCodeDetail.class.getName());
    return "view/system/sysCode/tree";
}


@RequestMapping
public void save(HttpServletResponse response,SysCode entity){
    try {
        SysCode target;
        if (entity.getId() != null) {
            target = sysCodeService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "code", "name", "isReserved", "description" });
        } else {
            target = entity;
        }
        sysCodeService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public String treeDataForSelect(String code,String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("系统代码树"));
    } else if (StringUtils.equals(id, "root")) {
        String hql = "from SysCode where parent.id is null and code='" + code + "' order by treeId asc";
        List<SysCode> nodeList = sysCodeService.findByQuery(hql);
        for (SysCode data : nodeList) {
            boolean isLeaf = data.getSysCodeDetails().size() == 0;
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getName());
            treeNode.setIsLeaf(isLeaf);
            treeNode.setIcon("1");
            treeNode.setType("data");
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "data")) {
        String hql = "from SysCodeDetail where sysCode.id=" + id + " order by treeId asc";
        List<SysCodeDetail> nodeList = sysCodeDetailService.findByQuery(hql);
        for (SysCodeDetail data : nodeList) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getName());
            treeNode.setIsLeaf(data.getIsLeaf());
            treeNode.setIcon("2");
            treeNode.setType("detail");
            treeBranch.addTreeNode(treeNode);
        }
    }
    model.addAttribute("msg", treeBranch.toJsonString(true));
    return "common/msg";
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    sysCodeService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


@RequestMapping
public String treeData(String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("系统代码树"));
    } else if (StringUtils.equals(id, "root")) {
        String hql = "from SysCode where parent.id is null order by treeId asc";
        List<SysCode> nodeList = sysCodeService.findByQuery(hql);
        for (SysCode data : nodeList) {
            boolean isLeaf = data.getSysCodeDetails().size() == 0;
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getName());
            treeNode.setIsLeaf(isLeaf);
            treeNode.setIcon("1");
            treeNode.setType("data");
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "data")) {
        String hql = "from SysCodeDetail where sysCode.id=" + id + " and parent is null order by treeId asc";
        List<SysCodeDetail> nodeList = sysCodeDetailService.findByQuery(hql);
        for (SysCodeDetail data : nodeList) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getName());
            treeNode.setIsLeaf(data.getIsLeaf());
            treeNode.setIcon("2");
            treeNode.setType("detail");
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "detail")) {
        String hql = "from SysCodeDetail where parent.id=" + id + " order by treeId asc";
        List<SysCodeDetail> nodeList = sysCodeDetailService.findByQuery(hql);
        for (SysCodeDetail data : nodeList) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getName());
            treeNode.setIsLeaf(data.getIsLeaf());
            treeNode.setIcon("2");
            treeNode.setType("detail");
            treeBranch.addTreeNode(treeNode);
        }
    }
    model.addAttribute("msg", treeBranch.toJsonString(true));
    return "common/msg";
}


}