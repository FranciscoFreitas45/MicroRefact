package com.zis.requirement.action;
 import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.SchoolBiz;
public class DepartmentInfoAddAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 private  SchoolBiz schoolBiz;

 private  String college;

 private  String institute;

 private  String partName;

 private  Integer years;

 private  Integer id;


public void setCollege(String college){
    this.college = college;
}


public String getInstitute(){
    return institute;
}


public void setPartName(String partName){
    this.partName = partName;
}


public void setInstitute(String institute){
    this.institute = institute;
}


public String getCollege(){
    return college;
}


public Integer getId(){
    return id;
}


@Validations(// 不能为空
requiredStrings = { @RequiredStringValidator(fieldName = "college", trim = true, key = "学校不能为空"), @RequiredStringValidator(fieldName = "institute", trim = true, key = "学院不能为空"), @RequiredStringValidator(fieldName = "partName", trim = true, key = "专业不能为空") }, requiredFields = { @RequiredFieldValidator(fieldName = "years", key = "学年制不能为空") })
public String addSchool(){
    ActionContext ctx = ActionContext.getContext();
    Departmentinfo dmi;
    try {
        if (id != null) {
            dmi = schoolBiz.findDepartmentInfoById(id);
            if (dmi != null) {
                dmi.setGmtModify(ZisUtils.getTS());
                dmi.setCollege(college);
                dmi.setInstitute(institute);
                dmi.setPartName(partName);
                dmi.setYears(years);
                schoolBiz.updateDepartmentInfo(dmi);
                logger.info("requirement.action.AddSchoolAction.addSchool--学院信息已存在,修改学年制成功");
                ctx.put("MSG", "操作成功");
                return SUCCESS;
            } else {
                return INPUT;
            }
        } else {
            dmi = new Departmentinfo();
            dmi.setCollege(college);
            dmi.setPartName(partName);
            dmi.setInstitute(institute);
            dmi.setYears(years);
            dmi.setGmtModify(ZisUtils.getTS());
            dmi.setGmtCreate(ZisUtils.getTS());
            try {
                schoolBiz.addDepartmentInfo(dmi);
            } catch (Exception e) {
                logger.error("院校信息已重复", e);
                this.addFieldError("ERROR", "院校信息已重复");
                return INPUT;
            }
            ctx.put("MSG", "操作成功");
            return SUCCESS;
        }
    } catch (Exception e) {
        logger.error("系统错误", e);
        throw new RuntimeException("系统错误");
    }
}


public void setSchoolBiz(SchoolBiz schoolBiz){
    this.schoolBiz = schoolBiz;
}


public void setYears(Integer years){
    this.years = years;
}


public SchoolBiz getSchoolBiz(){
    return schoolBiz;
}


public Integer getYears(){
    return years;
}


public void setId(Integer id){
    this.id = id;
}


public String getPartName(){
    return partName;
}


}