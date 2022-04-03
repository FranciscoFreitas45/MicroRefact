package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysMenuService;
import com.gbcom.system.domain.SysMenu;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.DTO.ZTreeBranch;
import com.gbcom.DTO.ZTreeNode;
@Controller
public class SysMenuController extends BaseCRUDActionController{

 private  String[] ICON_LIST;

@Autowired
 private  SysMenuService sysMenuService;

@Autowired
 private  SysUserManager sysUserManager;


@RequestMapping
@UserLog
public String add(Model model,String parentId){
    SysMenu sysMenu = new SysMenu();
    sysMenu.setIsValid(true);
    // 如需增加其他默认值请在此添加
    if (!StringHelper.isEmpty(parentId) && !"root".equals(parentId)) {
        sysMenu.setParent(sysMenuService.get(Long.valueOf(parentId)));
    }
    sysMenu.setIcon(ICON_LIST[0]);
    model.addAttribute("bean", sysMenu);
    model.addAttribute("ICON_LIST", ICON_LIST);
    return "view/system/sysMenu/input";
}


@RequestMapping
public String init(Model model){
    return "view/system/sysMenu/init";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysMenu sysMenu = sysMenuService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysMenu);
    model.addAttribute("ICON_LIST", ICON_LIST);
    return "view/system/sysMenu/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysMenu sysMenu = sysMenuService.get(id);
    model.addAttribute("bean", sysMenu);
    return "view/system/sysMenu/view";
}


@RequestMapping
public String move(Long id,Model model){
    SysMenu sysMenu = sysMenuService.findUniqueByProperty("id", id);
    List<SysMenu> sysMenusList;
    // SysCodeDetail codeDetail = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
    String hql = "from SysMenu where tree_id not like '" + sysMenu.getTreeId() + "%' order by treeId asc";
    sysMenusList = sysMenuService.find(hql);
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map;
    for (SysMenu sys : sysMenusList) {
        map = new HashMap<String, Object>();
        map.put("id", sys.getId());
        map.put("name", JspHelper.getLevelStr(sys.getTreeId(), "&nbsp;&nbsp;&nbsp;") + sys.getName());
        list.add(map);
    }
    model.addAttribute("bean", sysMenu);
    model.addAttribute("menuList", list);
    return "view/system/sysMenu/move";
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_MENU_EDIT));
    return "view/system/sysMenu/grid";
}


@RequestMapping
public String tree(Model model,HttpServletRequest request){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_MENU_EDIT));
    model.addAttribute("clazz", SysMenu.class.getName());
    return "view/system/sysMenu/tree";
}


@RequestMapping
public void save(HttpServletResponse response,SysMenu entity,HttpServletRequest request){
    try {
        SysMenu target;
        if (entity.getId() != null) {
            target = sysMenuService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "name", "privilege", "menuLevel", "url", "jsEvent", "isValid", "param", // "parent",
            "target", "icon" });
        /*                String parentId = request.getParameter("parent");
                if (!StringHelper.isEmpty(parentId)) {
                    target.setParent(sysMenuService.get(Long.valueOf(parentId)));
                }*/
        } else {
            target = entity;
        }
        throw new Exception();
    // sysMenuService.save(target);
    // sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void moveSave(HttpServletResponse response,HttpServletRequest request){
    String id = request.getParameter("id");
    String parentid = request.getParameter("parentid");
    SysMenu sysMenu = sysMenuService.findUniqueByProperty("id", Long.parseLong(id));
    Long oldParentId = null;
    SysMenu oldParent = sysMenu.getParent();
    if (oldParent != null) {
        oldParentId = oldParent.getId();
    }
    if (com.hc.core.utils.StringHelper.isEmpty(parentid)) {
        sysMenu.setParent(null);
    } else {
        SysMenu parentsysMenu = sysMenuService.findUniqueByProperty("id", Long.parseLong(parentid));
        sysMenu.setParent(parentsysMenu);
        if (parentsysMenu.getIsLeaf()) {
            parentsysMenu.setIsLeaf(false);
            sysMenuService.save(parentsysMenu);
        }
    }
    sysMenuService.save(sysMenu);
    if (oldParentId != null) {
        List<SysMenu> list = sysMenuService.findByQuery("from SysMenu where parent.id=" + oldParentId + " and id<>" + id);
        if (list.size() == 0) {
            oldParent.setIsLeaf(true);
            sysMenuService.save(oldParent);
        }
    }
    sendSuccessJSON(response, "移动成功");
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session){
    try {
        Page<SysMenu> pageModel = new Page<SysMenu>(page, rows, true);
        String hql = "from SysMenu order by treeId asc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysMenuService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    sysMenuService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


@RequestMapping
public String treeData(String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("根节点"));
    } else if (StringUtils.equals(id, "root")) {
        String hql = "from SysMenu where parent.id is null order by treeId asc";
        List<SysMenu> nodeList = sysMenuService.findByQuery(hql);
        for (SysMenu node : nodeList) {
            boolean isLeaf = node.getSysMenus().size() == 0;
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(node.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(node.getName());
            treeNode.setIcon(node.getIsValid() ? "1" : "2");
            treeNode.setType("data");
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "data")) {
        String hql = "from SysMenu where parent.id=" + id + " order by treeId asc";
        List<SysMenu> nodeList = sysMenuService.findByQuery(hql);
        for (SysMenu node : nodeList) {
            boolean isLeaf = node.getSysMenus().size() == 0;
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(node.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(node.getName());
            treeNode.setIcon(node.getIsValid() ? "1" : "2");
            treeNode.setType("data");
            treeBranch.addTreeNode(treeNode);
        }
    }
    String s = treeBranch.toJsonString(true);
    System.out.println(s);
    model.addAttribute("msg", s);
    return "common/msg";
}


}