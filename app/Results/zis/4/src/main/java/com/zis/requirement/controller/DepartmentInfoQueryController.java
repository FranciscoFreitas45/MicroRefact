package com.zis.requirement.controller;
 import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.SchoolBiz;
import com.zis.DTO.QueryUtil;
@Controller
@RequestMapping(value = "/requirement")
public class DepartmentInfoQueryController {

@Autowired
 private  SchoolBiz schoolBiz;


@RequiresPermissions(value = { "requirement:findAllSchoolInfo" })
@RequestMapping("/findAllSchoolInfo")
public String getAllInfo(String school,String institute,String partName,HttpServletRequest request,ModelMap ctx){
    // 分页查询
    Pageable page = WebHelper.buildPageRequest(request);
    Specification<Departmentinfo> spec = buildSpec(school, institute, partName);
    Page<Departmentinfo> pList = this.schoolBiz.findAllBySpecPage(spec, page);
    // 将返回结果存入ActionContext中
    ctx.put("AllSchoolInfo", pList.getContent());
    ctx.put("school", school);
    ctx.put("institute", institute);
    ctx.put("partName", partName);
    ctx.put("page", page.getPageNumber());
    if (pList.hasPrevious()) {
        ctx.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (pList.hasNext()) {
        ctx.put("nextPage", page.next().getPageNumber());
    }
    return "requirement/schoolInfo";
}


public Specification<Departmentinfo> buildSpec(String school,String institute,String partName){
    QueryUtil<Departmentinfo> query = new QueryUtil<Departmentinfo>();
    if (!StringUtils.isBlank(school)) {
        query.like("college", "%" + school + "%");
    }
    if (!StringUtils.isBlank(institute)) {
        query.like("institute", "%" + institute + "%");
    }
    if (!StringUtils.isBlank(partName)) {
        query.like("partName", "%" + partName + "%");
    }
    query.asc("college").asc("institute").asc("partName");
    return query.getSpecification();
}


@RequiresPermissions(value = { "requirement:school:view" })
@RequestMapping("/findSchoolInfo")
public String findInfo(String school,String institute,String partName,HttpServletRequest request,ModelMap ctx){
    // 分页查询
    Pageable page = WebHelper.buildPageRequest(request);
    Specification<Departmentinfo> spec = buildSpec(school, institute, partName);
    Page<Departmentinfo> pList = this.schoolBiz.findAllBySpecPage(spec, page);
    // 将返回结果存入ActionContext中
    ctx.put("allSchoolInfo", pList.getContent());
    ctx.put("school", school);
    ctx.put("institute", institute);
    ctx.put("partName", partName);
    ctx.put("page", page.getPageNumber());
    if (pList.hasPrevious()) {
        ctx.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (pList.hasNext()) {
        ctx.put("nextPage", page.next().getPageNumber());
    }
    return "requirement/schoolInfo";
}


}