package com.gbcom.system.controller;
 import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.SysDeptService;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.daoservice.SysPersonService;
import com.gbcom.system.domain.SysDept;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysPersonDept;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.PrivilegeCode;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.ReflectionUtils;
import com.hc.core.utils.StringHelper;
import com.gbcom.Interface.SysUserManager;
@Controller
public class SysPersonController extends BaseCRUDActionController{

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysPersonService sysPersonService;

@Autowired
 private  SysPersonDeptService sysPersonDeptService;

@Autowired
 private  SysDeptService sysDeptService;


@RequestMapping
@UserLog
public String add(Model model){
    SysPerson sysPerson = new SysPerson();
    // 如需增加其他默认值请在此添加
    model.addAttribute("bean", sysPerson);
    return "view/system/sysPerson/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysPerson sysPerson = sysPersonService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", sysPerson);
    return "view/system/sysPerson/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysPerson sysPerson = sysPersonService.get(id);
    model.addAttribute("bean", sysPerson);
    return "view/system/sysPerson/view";
}


@RequestMapping
public void upLoadPhoto(HttpServletResponse response,HttpServletRequest request,MultipartFile uploadFile,Integer type){
    try {
        String fileName = uploadFile.getOriginalFilename();
        String realPath = request.getSession().getServletContext().getRealPath("/html/avatars/");
        SysUser sysUser = sysUserManager.getSysUser();
        SysPerson sysPerson = sysUser.getPerson();
        if (sysPerson == null) {
            sendFailureJSON(response, "请先绑定身份信息，再进行图像上传");
        }
        File file = new File(realPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        String photoName = "";
        if (type == 1) {
            photoName = "avatar" + sysPerson.getId() + ".png";
            sysPerson.setAvatarUrl("../html/avatars/" + photoName);
        } else {
            photoName = "proFile" + sysPerson.getId() + ".jpg";
            sysPerson.setProfilePhotoUrl("../html/avatars/" + photoName);
        }
        FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(realPath, photoName));
        sysPersonService.save(sysPerson);
    } catch (Exception e) {
        sendFailureJSON(response, e.getMessage());
    }
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_PERSON_EDIT));
    return "view/system/sysPerson/grid";
}


@RequestMapping
public void save(HttpServletResponse response,SysPerson entity,HttpServletRequest request){
    try {
        SysPerson target;
        if (entity.getId() != null) {
            target = sysPersonService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "age", "bornDate", "bornPlace", "card", "code", "email", "faxTel", "memo", "mobile", "msnCode", "name", "officeTel", "qqCode", "sex", "workYear", "zipcode" });
        } else {
            target = entity;
        }
        sysPersonService.save(target);
        // 所属部门
        Set<SysPersonDept> personDepts = target.getSysPersonDepts();
        for (SysPersonDept personDept : personDepts) {
            sysPersonDeptService.delete(personDept);
        }
        String sysDeptId = request.getParameter("sysDeptId");
        if (!StringHelper.isEmpty(sysDeptId)) {
            SysPersonDept personDept = new SysPersonDept();
            personDept.setPerson(target);
            personDept.setDept(sysDeptService.get(Long.valueOf(sysDeptId)));
            personDept.setIsValid(true);
            personDept.setOrderNo(JspHelper.getLong(request.getParameter("orderNo")));
            sysPersonDeptService.save(personDept);
        }
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletRequest request,HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session){
    try {
        Page<SysPerson> pageModel = new Page<SysPerson>(page, rows, true);
        String hql = "select sp" + " from SysPerson sp left join sp.sysPersonDepts spd " + " left join spd.dept dept ";
        // 增加自定义查询条件
        String unitName = request.getParameter("unitName");
        if (StringUtils.isNotEmpty(unitName)) {
            List<SysDept> sysDepts = sysDeptService.findByQuery("from SysDept where name like '%" + unitName + "%' and isTag=" + Constants.FLAG_TRUE);
            if (sysDepts.size() > 0) {
                String unitHql = "(select id from SysDept where isTag=" + Constants.FLAG_FALSE + " and (";
                List<String> likeHqls = new ArrayList<String>();
                for (SysDept sysDept : sysDepts) {
                    likeHqls.add("dept.treeId like '" + sysDept.getTreeId() + "%'");
                }
                unitHql += StringUtils.join(likeHqls, " or ") + "))";
                hql += " where dept.id in " + unitHql;
            } else {
                hql += " where 1=2";
            }
        }
        hql += " order by dept.treeId asc,spd.orderNo asc,sp.name asc";
        // 执行查询
        // 统一添加别名
        filters = filters.replaceAll("\"field\":\"(?!dept\\.name)", "\"field\":\"sp.");
        // 表头排序，单位排序有问题
        filters = filters.replaceAll("\"orderColumn\":\"company", "\"orderColumn\":\"dept");
        // 表头排序
        filters = filters.replaceAll("\"orderColumn\":\"(?!dept\\.name|\")", "\"orderColumn\":\"sp.");
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysPersonService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public String treeDataForSelect(String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("根节点"));
    } else if (StringUtils.equals(id, "root")) {
        // 单位
        String hql = "from SysDept where parent.id is null order by treeId asc";
        List<SysDept> nodeList = sysDeptService.findByQuery(hql);
        for (SysDept data : nodeList) {
            boolean isLeaf = data.getSysPersonDepts().size() == 0;
            if (isLeaf) {
                isLeaf = data.getSysPersonDepts().size() == 0;
            }
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(data.getName());
            if (data.getIsCompany() != null && data.getIsCompany()) {
                treeNode.setIcon("1");
                treeNode.setType("company");
            } else {
                treeNode.setIcon("2");
                treeNode.setType("dept");
            }
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "company") || StringUtils.equals(type, "dept")) {
        // 单位/部门
        String hql = "from SysDept where parent.id=" + id + " order by treeId asc";
        List<SysDept> nodeList = sysDeptService.findByQuery(hql);
        for (SysDept data : nodeList) {
            boolean isLeaf = data.getSysPersonDepts().size() == 0;
            if (isLeaf) {
                isLeaf = data.getSysPersonDepts().size() == 0;
            }
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(data.getName());
            if (data.getIsCompany() != null && data.getIsCompany()) {
                treeNode.setIcon("1");
                treeNode.setType("company");
            } else {
                treeNode.setIcon("2");
                treeNode.setType("dept");
            }
            treeBranch.addTreeNode(treeNode);
        }
        // 人员
        List<SysPersonDept> personDeptList = sysPersonDeptService.findByQuery("from SysPersonDept where dept.id=" + id + " order by orderNo,person.name asc");
        for (SysPersonDept personDept : personDeptList) {
            SysPerson person = personDept.getPerson();
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(person.getId()));
            treeNode.setIsLeaf(true);
            treeNode.setName(person.getName());
            treeNode.setIcon("3");
            treeNode.setType("person");
            treeBranch.addTreeNode(treeNode);
        }
    }
    String s = treeBranch.toJsonString(false);
    model.addAttribute("msg", s);
    return "common/msg";
}


@InitBinder
public void initBinder(WebDataBinder binder){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    SysPerson person = sysPersonService.get(id);
    if (person.getSysUsers().size() > 0) {
        sendFailureJSON(response, "删除失败，已被用户引用！");
    } else {
        Set<SysPersonDept> personDepts = person.getSysPersonDepts();
        for (SysPersonDept personDept : personDepts) {
            sysPersonDeptService.delete(personDept);
        }
        sysPersonService.delete(id);
        sendSuccessJSON(response, "删除成功");
    }
}


@RequestMapping
public String upLoadUrl(HttpServletResponse response,Model model){
    return "view/system/sysPerson/upLoadPhoto";
}


}