package org.jeecgframework.web.system.pojo.base;
 import org.codehaus.jackson.annotate.JsonIgnore;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import javax.persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity
@Table(name = "t_s_base_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class TSBaseUser extends IdEntity{

 private  long serialVersionUID;

@Excel(name = "用户名", width = 15)
 private  String userName;

@Excel(name = "真实姓名", width = 15)
 private  String realName;

 private  String browser;

@Excel(name = "角色编码(多个角色编码用逗号分隔，非必填)", width = 50)
 private  String userKey;

 private  String password;

 private  Short activitiSync;

 private  Short status;

 private  Short deleteFlag;

 private  byte[] signature;

 private  String userNameEn;

@Excel(name = "组织机构编码(多个组织机构编码用逗号分隔，非必填)", width = 50)
 private  String departid;

 private  List<TSUserOrg> userOrgList;

 private  TSDepart currentDepart;


public void setPassword(String password){
    this.password = password;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setSignature(byte[] signature){
    this.signature = signature;
}


@Column(name = "delete_flag")
public Short getDeleteFlag(){
    return deleteFlag;
}


@Column(name = "status")
public Short getStatus(){
    return this.status;
}


@Column(name = "activitisync")
public void setActivitiSync(Short activitiSync){
    this.activitiSync = activitiSync;
}


@Column(name = "departid", length = 32)
public String getDepartid(){
    return departid;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Short getActivitiSync(){
    return activitiSync;
}


public void setDepartid(String departid){
    this.departid = departid;
}


@Column(name = "userkey", length = 200)
public String getUserKey(){
    return userKey;
}


@Column(name = "username", nullable = false)
public String getUserName(){
    return this.userName;
}


public void setBrowser(String browser){
    this.browser = browser;
}


@JsonIgnore
@OneToMany(mappedBy = "tsUser")
public List<TSUserOrg> getUserOrgList(){
    return userOrgList;
}


@Column(name = "user_name_en")
public String getUserNameEn(){
    return userNameEn;
}


@Column(name = "signature", length = 3000)
public byte[] getSignature(){
    return signature;
}


public void setUserKey(String userKey){
    this.userKey = userKey;
}


@Column(name = "realname", length = 50)
public String getRealName(){
    return this.realName;
}


public void setStatus(Short status){
    this.status = status;
}


@Column(name = "password", length = 100)
public String getPassword(){
    return this.password;
}


public void setCurrentDepart(TSDepart currentDepart){
    this.currentDepart = currentDepart;
}


public void setDeleteFlag(Short deleteFlag){
    this.deleteFlag = deleteFlag;
}


public void setUserNameEn(String userNameEn){
    this.userNameEn = userNameEn;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("TSBaseUser [userName=");
    builder.append(userName);
    builder.append(", realName=");
    builder.append(realName);
    builder.append(", browser=");
    builder.append(browser);
    builder.append(", userKey=");
    builder.append(userKey);
    builder.append(", password=");
    builder.append(password);
    builder.append(", activitiSync=");
    builder.append(activitiSync);
    builder.append(", status=");
    builder.append(status);
    builder.append(", deleteFlag=");
    builder.append(deleteFlag);
    builder.append(", signature=");
    builder.append(Arrays.toString(signature));
    builder.append(", userNameEn=");
    builder.append(userNameEn);
    builder.append(", departid=");
    builder.append(departid);
    builder.append(", userOrgList=");
    builder.append(userOrgList);
    builder.append(", currentDepart=");
    builder.append(currentDepart);
    builder.append("]");
    return builder.toString();
}


public void setUserOrgList(List<TSUserOrg> userOrgList){
    this.userOrgList = userOrgList;
}


@Column(name = "browser", length = 20)
public String getBrowser(){
    return browser;
}


@Transient
public TSDepart getCurrentDepart(){
    return currentDepart;
}


}