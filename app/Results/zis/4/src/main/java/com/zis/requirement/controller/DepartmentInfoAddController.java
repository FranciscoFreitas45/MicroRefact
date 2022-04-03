package com.zis.requirement.controller;
 import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.SchoolBiz;
import com.zis.requirement.dto.AddSchoolDTO;
@Controller
@RequestMapping(value = "/requirement")
public class DepartmentInfoAddController {

 private  Logger logger;

@Autowired
 private  SchoolBiz schoolBiz;


@RequiresPermissions(value = { "requirement:addSchoolAction" })
@RequestMapping(value = "/addSchoolAction")
public String addSchool(AddSchoolDTO dto,BindingResult br,Integer id,ModelMap ctx){
    if (br.hasErrors()) {
        return "requirement/addSchoolInfo";
    }
    Departmentinfo dmi;
    try {
        if (id != null) {
            dmi = schoolBiz.findDepartmentInfoById(id);
            if (dmi != null) {
                dmi.setGmtModify(ZisUtils.getTS());
                dmi.setCollege(dto.getCollege());
                dmi.setInstitute(dto.getInstitute());
                dmi.setPartName(dto.getPartName());
                dmi.setYears(dto.getYears());
                schoolBiz.updateDepartmentInfo(dmi);
                logger.info("requirement.action.AddSchoolAction.addSchool--学院信息已存在,修改学年制成功");
                ctx.put("MSG", "操作成功");
                return "redirect:/requirement/updateSchoolPre";
            } else {
                return "error";
            }
        } else {
            dmi = new Departmentinfo();
            dmi.setCollege(dto.getCollege());
            dmi.setPartName(dto.getPartName());
            dmi.setInstitute(dto.getInstitute());
            dmi.setYears(dto.getYears());
            dmi.setGmtModify(ZisUtils.getTS());
            dmi.setGmtCreate(ZisUtils.getTS());
            try {
                schoolBiz.addDepartmentInfo(dmi);
            } catch (Exception e) {
                logger.error("院校信息已重复", e);
                ctx.put("actionError", "院校信息已重复");
                return "error";
            }
            ctx.put("MSG", "操作成功");
            return "redirect:/requirement/updateSchoolPre";
        }
    } catch (Exception e) {
        logger.error("系统错误", e);
        throw new RuntimeException("系统错误");
    }
}


}