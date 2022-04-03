package com.gbcom.system.controller;
 import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysAreaNesService;
import com.gbcom.system.daoservice.SysAreaService;
import com.gbcom.system.domain.SysArea;
import com.gbcom.system.domain.SysAreaNes;
import com.gbcom.system.domain.SysCodeDetail;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.domain.SysUserRole;
import com.gbcom.system.manager.SysAreaManager;
import com.gbcom.system.manager.SysCodeManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.JdbcTemplateUtil;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.DTO.ZTreeBranch;
import com.gbcom.DTO.ZTreeNode;
import com.gbcom.DTO.QueryTranslateJq;
@Controller
public class SysAreaController extends BaseCRUDActionController<SysArea>{

 private  Logger LOG;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysAreaService sysAreaService;

@Autowired
 private  SysAreaNesService sysAreaNesService;

@Autowired
 private  SysCodeManager sysCodeManager;

@Autowired
 private  SysAreaManager sysAreaManager;


@RequestMapping
@UserLog
public String add(Model model,String parentId){
    SysArea sysCode = new SysArea();
    // 如需增加其他默认值请在此添加
    parentId = parentId.equals("root") ? "0" : parentId;
    model.addAttribute("bean", sysCode);
    model.addAttribute("parentId", parentId);
    model.addAttribute("isAdd", true);
    return "view/system/sysArea/input";
}


@RequestMapping
public String init(Model model){
    return "view/system/sysArea/init";
}


@RequestMapping
public String tree(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_AREA_EDIT));
    model.addAttribute("clazz", SysArea.class.getName());
    model.addAttribute("clazzDetail", "area");
    return "view/system/sysArea/tree";
}


@RequestMapping
public void save(HttpServletRequest request,HttpServletResponse response,String parentId,SysArea entity){
    try {
        SysArea target;
        if (entity.getId() != null) {
            target = sysAreaService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "areaName", "description" });
        } else {
            String hql = "from SysArea a where a.areaName = ?";
            if (sysAreaService.find(hql, new String[] { entity.getAreaName() }).size() != 0) {
                sendFailureJSON(response, "名称已存在，请更换");
                return;
            }
            target = entity;
            target.setAreaCode(entity.getAreaName());
            target.setIsReserved(Boolean.TRUE);
        }
        if (!StringHelper.isEmpty(parentId)) {
            if (parentId.equals("0")) {
                target.setParent(null);
                target.setLayer(1L);
            // target.setTreeId(treeId)
            } else {
                SysArea parent = sysAreaService.get(Long.valueOf(parentId));
                target.setLayer(parent.getLayer() + 1);
                target.setParent(parent);
            }
        }
        if (target.getIsLeaf() == null) {
            log.error("the leaf value must not be null,,so set false");
            target.setIsReserved(Boolean.FALSE);
        }
        sysAreaService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@Deprecated
public String treeDataForSelect(String code,String type,String id,String icon,Model model){
    // select.js
    try {
        SysUser sysuser = sysUserManager.getSysUser();
        // 0:系统级 1：商业wifi 2：区域
        int roleType = 0;
        for (SysUserRole role : sysuser.getSysUserRoles()) {
            // 角色
            // 为多对多会有问题，暂时只考虑多对一情况。
            roleType = role.getRole().getRoleType();
        }
        ZTreeBranch treeBranch = new ZTreeBranch();
        treeBranch.setIcons(icon.split(","));
        if (StringUtils.isEmpty(id)) {
            treeBranch.addTreeNode(treeBranch.getRootNode("区域分级"));
        } else if (StringUtils.equals(id, "root")) {
            String hql = "";
            if (roleType == 2) {
                // 如果是区域用户，新增用户只能够选择，该区域用户管理的区域的子区域
                SysArea area = sysuser.getArea();
                hql = "from SysArea where  parent=" + area.getId() + " order by id asc";
            } else {
                hql = "from SysArea where  parent=NULL order by id asc";
            }
            List<SysArea> nodeList = sysAreaService.findByQuery(hql);
            for (SysArea data : nodeList) {
                // boolean isLeaf = data.getSysCodeDetails().size() == 0;
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(String.valueOf(data.getId()));
                treeNode.setName(data.getAreaName());
                treeNode.setIsLeaf(false);
                treeNode.setIcon("1");
                treeNode.setType("data");
                treeBranch.addTreeNode(treeNode);
            }
        } else {
            String hql = "from SysArea where parent.id=" + id + " order by id asc";
            List<SysArea> nodeList = sysAreaService.findByQuery(hql);
            for (SysArea data : nodeList) {
                // boolean isLeaf = data.getSysCodeDetails().size() == 0;
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(String.valueOf(data.getId()));
                treeNode.setName(data.getAreaName());
                treeNode.setIsLeaf(true);
                treeNode.setIcon("1");
                treeNode.setType("data");
                treeBranch.addTreeNode(treeNode);
            }
        }
        model.addAttribute("msg", treeBranch.toJsonString(true));
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        model.addAttribute("msg", "操作失败");
    } finally {
        return "common/msg";
    }
}


public List<Map<String,Object>> queryRight(String replace){
    if (StringUtils.isEmpty(replace)) {
        return new ArrayList<Map<String, Object>>(0);
    }
    String sql = "SELECT b.gw_id,a.name ,a.street_address,b.manage_mac ,b.ap_name ,b.sys_model,  b.manageip, b.sw_version " + "FROM v3_hotspot AS a  RIGHT JOIN v3_apdevice AS b ON a.gw_id=b.gw_id " + "WHERE  b.gw_id LIKE '%sgwId%' OR a.name LIKE '%sgwId%' OR a.street_address LIKE '%sgwId%' OR b.manage_mac LIKE '%sgwId%' " + "OR b.ap_name  LIKE '%sgwId%' OR b.sys_model LIKE '%sgwId%' OR  b.manageip LIKE '%sgwId%' OR b.sw_version LIKE '%sgwId%' " + "GROUP BY b.gw_id";
    sql = sql.replace("sgwId", replace);
    // return jdbcTemplate.queryForList(sql);
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        LOG.info("queryRight FROM DB SQL=" + sql);
        con = JdbcTemplateUtil.getDBConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gw_id", rs.getString(1));
            map.put("name", rs.getString(2));
            map.put("street_address", rs.getString(3));
            map.put("manage_mac", rs.getString(4));
            map.put("ap_name", rs.getString(5));
            map.put("sys_mode", rs.getString(6));
            map.put("manageip", rs.getString(7));
            map.put("sw_version", rs.getString(8));
            list.add(map);
        }
    } catch (SQLException ex) {
        LOG.error("SQLException ：" + ex.getMessage());
    } catch (ClassNotFoundException e) {
        LOG.error("ClassNotFoundException ：" + e.getMessage());
    } finally {
        JdbcTemplateUtil.freeDBResource(con, ps, rs);
    }
    return list;
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    try {
        SysArea sysArea = sysAreaService.get(id);
        List<SysArea> sysAreaList = sysAreaManager.getChild(sysArea);
        List<String> hotspotList = sysAreaManager.sysAreaHots(id);
        if ((sysAreaList != null && sysAreaList.size() > 0) || (hotspotList != null && hotspotList.size() > 0)) {
            // DataIntegrityViolationException 提示用户手动删除
            sendFailureJSON(response, "该节点下已经存在子节点，不能删除！");
            return;
        }
        sysAreaService.delete(id);
        sendSuccessJSON(response, "删除成功");
    } catch (DataIntegrityViolationException e) {
        LOG.error(e.getMessage(), e);
        sendFailureJSON(response, "分组正在使用……（存在子节点或者已分配给用户）");
        return;
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public String searhMngNes(Model model,Long id,String sgwId){
    if (sgwId == null) {
        sgwId = "";
    } else {
        try {
            sgwId = new String(sgwId.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    SysArea sysArea = sysAreaService.get(id);
    Set<String> idSet = new HashSet<String>();
    // 获取当前用户已拥有的权限
    Set<SysAreaNes> nes = sysArea.getNes();
    for (SysAreaNes ne : nes) {
        idSet.add(ne.getNeID());
    }
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    List<String> groupIDS = sysAreaNesService.getAllHots();
    // 获取系统的所有权限
    for (Map<String, Object> eachMap : queryRight(sgwId)) {
        if (idSet.contains(eachMap.get("gw_id"))) {
            // 已添加
            continue;
        }
        if (groupIDS.contains(eachMap.get("gw_id"))) {
            continue;
        }
        /*
			 * ApDevice dev = apDeviceManager.getGwDevByGw(gw); if (dev == null)
			 * continue;
			 */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gwId", eachMap.get("gw_id"));
        // map.put("dev", dev);
        // 如果用户已经拥有该权限则被选中，页面显示时选框被勾选。
        map.put("isChecked", false);
        StringBuffer name = new StringBuffer("");
        nameAppend(name, eachMap.get("gw_id"));
        nameAppend(name, eachMap.get("name"));
        nameAppend(name, eachMap.get("street_address"));
        // nameAppend(name, eachMap.get("manage_mac"));
        nameAppend(name, eachMap.get("ap_name"));
        nameAppend(name, eachMap.get("sys_mode"));
        nameAppend(name, eachMap.get("manageip"));
        nameAppend(name, eachMap.get("sw_version"));
        map.put("hotspot", name.toString());
        list.add(map);
    }
    // List rst = findChecked(sysArea,sgwId);
    List rst = new ArrayList();
    rst.addAll(list);
    // 按钮权限
    SysCodeDetail privilegeType = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
    model.addAttribute("buttonType", privilegeType.getId());
    model.addAttribute("list", rst);
    model.addAttribute("id", id);
    model.addAttribute("bean", sysArea);
    return "view/system/sysArea/searhMngNes";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysArea sysCode = sysAreaService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysCode);
    return "view/system/sysArea/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysArea sysCode = sysAreaService.get(id);
    model.addAttribute("bean", sysCode);
    return "view/system/sysArea/view";
}


@RequestMapping
public String setMngNes(Model model,Long id){
    model.addAttribute("id", id);
    SysArea sysArea = sysAreaService.get(id);
    model.addAttribute("bean", sysArea);
    Set<String> idSet = new HashSet<String>();
    // 获取当前用户已拥有的权限
    Set<SysAreaNes> nes = sysArea.getNes();
    for (SysAreaNes ne : nes) {
        idSet.add(ne.getNeID());
    }
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    List<String> groupIDS = sysAreaNesService.getAllHots();
    // 获取系统的所有权限
    // 按钮权限
    SysCodeDetail privilegeType = sysCodeManager.getCodeDetailByCode(Constants.SYS_PRIVILEGE_TYPE, Constants.SYS_PRIVILEGE_TYPE_BUTTON);
    model.addAttribute("buttonType", privilegeType.getId());
    model.addAttribute("list", list);
    return "view/system/sysArea/setMngNes";
}


@RequestMapping
@UserLog
public String grid(Model model,Long id){
    // 判断是否有编辑权限
    if (id != null) {
        model.addAttribute("codeId", id);
    }
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_AREA_EDIT));
    return "view/system/sysArea/grid";
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,Long codeId,HttpSession session){
    try {
        Page<SysArea> pageModel = new Page<SysArea>(page, rows, true);
        String hql = "from SysArea order by id ";
        if (codeId != null) {
            hql = "from SysArea where parent.id=" + codeId + " order by id ASC";
        }
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysAreaService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_UNKNOW)
public void saveMngNes(HttpServletResponse response,Long id,HttpServletRequest request){
    try {
        String[] ids = request.getParameterValues("mngNesId");
        Set<String> keys = new HashSet<String>();
        if (ids != null) {
            CollectionUtils.addAll(keys, ids);
        }
        SysArea sysArea = sysAreaService.get(id);
        if (!sysArea.getIsLeaf()) {
            sendFailureJSON(response, "非法操作（不是叶子节点，）");
        }
        Set<SysAreaNes> nes = sysArea.getNes();
        for (SysAreaNes ne : nes) {
            ne.setArea(null);
            sysAreaNesService.update(ne);
        // sysAreaNesService.delete(ne);
        }
        Set<SysAreaNes> sets = new HashSet<SysAreaNes>();
        for (String key : keys) {
            SysAreaNes sysAreaNes = new SysAreaNes();
            sysAreaNes.setArea(sysArea);
            sysAreaNes.setNeID(key);
            sysAreaNes.setNeName("Dev=" + id);
            sysAreaNes.setFkTable("v3_apdevice");
            sysAreaNes.setFkCol("id");
            sysAreaNes.setFkValue(key);
            sets.add(sysAreaNes);
        // sysAreaNesService.save(sysAreaNes);
        }
        sysArea.setNes(sets);
        sysAreaService.save(sysArea);
        sysAreaNesService.deleteInvalid();
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void setUnGroupNes(HttpServletResponse response,HttpServletRequest request){
    try {
        String[] ids = request.getParameterValues("mngNesId");
        sysAreaNesService.deleteSysAreaNesByNeIds(ids);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


public void nameAppend(StringBuffer rst,Object appended){
    if (appended != null) {
        if (!StringUtils.isEmpty(appended.toString())) {
            rst.append(appended + "/");
        }
    }
}


@RequestMapping
public String treeData(String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    // treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("区域分级"));
    } else if (StringUtils.equals(id, "root")) {
        String hql = "from SysArea where  parent=NULL order by id asc";
        List<SysArea> nodeList = sysAreaService.findByQuery(hql);
        for (SysArea data : nodeList) {
            boolean isLeaf = data.getIsLeaf();
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getAreaName());
            if (isLeaf) {
                treeNode.setIsLeaf(true);
                treeNode.setIcon("2");
                treeNode.setType("leaf");
            } else {
                treeNode.setIsLeaf(false);
                treeNode.setIcon("1");
                treeNode.setType("data");
            }
            treeBranch.addTreeNode(treeNode);
        }
    } else {
        String hql = "from SysArea where parent.id=" + id + " order by id asc";
        List<SysArea> nodeList = sysAreaService.findByQuery(hql);
        for (SysArea data : nodeList) {
            boolean isLeaf = data.getIsLeaf();
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setName(data.getAreaName());
            if (isLeaf) {
                treeNode.setIsLeaf(true);
                treeNode.setIcon("2");
                treeNode.setType("leaf");
            } else {
                treeNode.setIsLeaf(false);
                treeNode.setIcon("1");
                treeNode.setType("data");
            }
            treeBranch.addTreeNode(treeNode);
        }
    }
    model.addAttribute("msg", treeBranch.toJsonString(true));
    return "common/msg";
}


}