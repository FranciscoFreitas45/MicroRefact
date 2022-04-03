package com.zis.requirement.action;
 import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.SchoolBiz;
public class BookAmountAddPreAction extends ActionSupport{

 private  long serialVersionUID;

 private  SchoolBiz schoolBiz;

 private  Integer id;


public SchoolBiz getSchoolBiz(){
    return schoolBiz;
}


public String amountPre(){
    if (id == null) {
        return INPUT;
    } else {
        Departmentinfo di = schoolBiz.findDepartmentInfoById(id);
        if (di == null) {
            return INPUT;
        }
        ActionContext ctx = ActionContext.getContext();
        ctx.put("did", id);
        ctx.put("college", di.getCollege());
        ctx.put("institute", di.getInstitute());
        ctx.put("partName", di.getPartName());
        ctx.put("grade", di.getYears());
        return SUCCESS;
    }
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setSchoolBiz(SchoolBiz schoolBiz){
    this.schoolBiz = schoolBiz;
}


}