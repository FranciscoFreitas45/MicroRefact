package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.jeecgframework.poi.excel.annotation.Excel;
public class TSUser extends TSBaseUser{

 private  long serialVersionUID;

 private  String signatureFile;

 private  String mobilePhone;

 private  String officePhone;

 private  String email;

 private  java.lang.String portrait;

 private  java.lang.String devFlag;

 private  String userType;

 private  String personType;

 private  String sex;

 private  String empNo;

 private  String citizenNo;

 private  String fax;

 private  String address;

 private  String post;

 private  String memo;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;


@Column(name = "portrait", length = 100)
public String getPortrait(){
    return portrait;
}


@Column(name = "officePhone", length = 20)
public String getOfficePhone(){
    return this.officePhone;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "signatureFile", length = 100)
public String getSignatureFile(){
    return this.signatureFile;
}


@Column(name = "person_type")
public String getPersonType(){
    return personType;
}


@Column(name = "post")
public String getPost(){
    return post;
}


@Column(name = "emp_no")
public String getEmpNo(){
    return empNo;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "mobilePhone", length = 30)
public String getMobilePhone(){
    return this.mobilePhone;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "address")
public String getAddress(){
    return address;
}


@Column(name = "dev_flag", length = 2)
public String getDevFlag(){
    return devFlag;
}


@Column(name = "user_type")
public String getUserType(){
    return userType;
}


@Column(name = "fax")
public String getFax(){
    return fax;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "memo")
public String getMemo(){
    return memo;
}


@Column(name = "citizen_no")
public String getCitizenNo(){
    return citizenNo;
}


@Column(name = "sex")
public String getSex(){
    return sex;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


}