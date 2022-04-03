package com.zis.requirement.controller;
 import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.SchoolBiz;
@Controller
@RequestMapping(value = "/requirement")
public class DepartmentInfoUpdatePreController {

@Autowired
 private  SchoolBiz schoolBiz;


@RequiresPermissions(value = { "requirement:school:saveOrUpdate" })
@RequestMapping(value = "/updateSchoolPre")
public String getInfo(Integer id,ModelMap ctx){
    if (id == null) {
        return "requirement/addSchoolInfo";
    } else {
        Departmentinfo dmi = schoolBiz.findDepartmentInfoById(id);
        if (dmi != null) {
            ctx.put("id", id);
            ctx.put("college", dmi.getCollege());
            ctx.put("institute", dmi.getInstitute());
            ctx.put("partName", dmi.getPartName());
            ctx.put("years", dmi.getYears());
            return "requirement/addSchoolInfo";
        } else {
            ctx.put("actionError", "院校信息不存在");
            return "error";
        }
    }
}


}