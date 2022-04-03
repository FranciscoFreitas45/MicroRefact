package com.jeecg.demo.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jeecg_demo_excel", schema = "")
@SuppressWarnings("serial")
public class JeecgDemoExcelEntity {

 private  java.lang.String id;

@Excel(name = "姓名", width = 15)
 private  java.lang.String name;

@Excel(name = "生日", format = "yyyy-MM-dd")
 private  java.util.Date birthday;

@Excel(name = "性别", width = 15, dicCode = "sex")
 private  java.lang.String sex;

@Excel(name = "部门", dictTable = "t_s_depart", dicCode = "id", dicText = "departname")
 private  java.lang.String depart;

@Excel(name = "测试替换", width = 15, replace = { "男_1", "女_0" })
 private  java.lang.String fdReplace;

@Excel(name = "测试转换", width = 15, exportConvert = true, importConvert = true)
 private  java.lang.String fdConvert;


public void setName(java.lang.String name){
    this.name = name;
}


public void setDepart(java.lang.String depart){
    this.depart = depart;
}


@Column(name = "name", nullable = true, length = 100)
public java.lang.String getName(){
    return name;
}


@Column(name = "birthday", nullable = true, length = 20)
public java.util.Date getBirthday(){
    return birthday;
}


public void setFdReplace(java.lang.String fdReplace){
    this.fdReplace = fdReplace;
}


public void setSex(java.lang.String sex){
    this.sex = sex;
}


@Column(name = "FD_CONVERT", nullable = true, length = 255)
public java.lang.String getFdConvert(){
    return this.fdConvert;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setBirthday(java.util.Date birthday){
    this.birthday = birthday;
}


@Column(name = "DEPART", nullable = true, length = 36)
public java.lang.String getDepart(){
    return this.depart;
}


@Column(name = "SEX", nullable = true, length = 3)
public java.lang.String getSex(){
    return this.sex;
}


public void setFdConvert(java.lang.String fdConvert){
    this.fdConvert = fdConvert;
}


public String convertgetFdConvert(){
    return this.fdConvert + "元";
}


public void setId(java.lang.String id){
    this.id = id;
}


public void convertsetFdConvert(String fdConvert){
    this.fdConvert = fdConvert.replace("元", "");
}


@Column(name = "FD_REPLACE", nullable = true, length = 255)
public java.lang.String getFdReplace(){
    return this.fdReplace;
}


}