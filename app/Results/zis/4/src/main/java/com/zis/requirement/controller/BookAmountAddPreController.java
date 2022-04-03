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
public class BookAmountAddPreController {

@Autowired
 private  SchoolBiz schoolBiz;


@RequiresPermissions(value = { "requirement:books:input" })
@RequestMapping(value = "/addAmountPreAction")
public String amountPre(Integer id,ModelMap ctx){
    if (id == null) {
        return "error";
    } else {
        Departmentinfo di = schoolBiz.findDepartmentInfoById(id);
        if (di == null) {
            return "error";
        }
        ctx.put("did", id);
        ctx.put("college", di.getCollege());
        ctx.put("institute", di.getInstitute());
        ctx.put("partName", di.getPartName());
        ctx.put("grade", di.getYears());
        return "requirement/amountInfo";
    }
}


}