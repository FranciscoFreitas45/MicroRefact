package com.zis.requirement.dto;
 import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
public class AddSchoolDTO {

@NotBlank(message = "学校不能为空")
 private  String college;

@NotBlank(message = "学院不能为空")
 private  String institute;

@NotBlank(message = "专业不能为空")
 private  String partName;

@NotNull(message = "学年制不能为空")
 private  Integer years;


public void setCollege(String college){
    this.college = college;
}


public void setYears(Integer years){
    this.years = years;
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


public Integer getYears(){
    return years;
}


public String getPartName(){
    return partName;
}


}